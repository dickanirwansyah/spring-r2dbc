package com.reactive.servicereactive.service;

import com.alibaba.fastjson.JSON;
import com.reactive.servicereactive.base.BaseService;
import com.reactive.servicereactive.model.request.AuditLogRequest;
import com.reactive.servicereactive.model.request.CustomerRequest;
import com.reactive.servicereactive.model.response.CustomerLogResponse;
import com.reactive.servicereactive.model.response.CustomerResponse;
import com.reactive.servicereactive.repository.CustomerRepository;
import com.reactive.servicereactive.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UpdateCustomerService implements BaseService<CustomerLogResponse, CustomerRequest> {

    private CustomerRepository customerRepository;
    private AuditLogService auditLogService;
    public UpdateCustomerService(CustomerRepository customerRepository,
                                 AuditLogService auditLogService){
        this.customerRepository = customerRepository;
        this.auditLogService = auditLogService;
    }

    @Override
    public Mono<CustomerLogResponse> execute(CustomerRequest request) {
       log.info("update customer -> {} ", JSON.toJSONString(request));
       return Mono.zip(doUpdateCustomer(request), auditLogService.execute(AuditLogRequest
               .builder()
                       .remarks(JSON.toJSONString(request))
               .build())).map(objectTuple -> CustomerLogResponse
               .builder()
               .id(objectTuple.getT1().getCustomerId())
               .code(objectTuple.getT1().getCustomerCode())
               .dob(objectTuple.getT1().getDob())
               .adress(objectTuple.getT1().getAddress())
               .phoneNumber(objectTuple.getT1().getPhoneNumber())
               .fullname(objectTuple.getT1().getFullName())
               .email(objectTuple.getT1().getEmail())
               .code(objectTuple.getT1().getCustomerCode())
               .remarks(objectTuple.getT2().getRemarks())
               .build());
    }

    private Mono<CustomerResponse> doUpdateCustomer(CustomerRequest request){
       return this.customerRepository.findById(request.getCustomerId())
                .flatMap(customers -> {
                    log.info("update execute customer.. {} ",customers.getId());
                    customers.setEmail(request.getCustomerEmail());
                    customers.setFullname(request.getCustomerFullName());
                    customers.setPhoneNumber(request.getCustomerPhoneNumber());
                    customers.setFullname(request.getCustomerFullName());
                    customers.setDob(DateUtil.convertData(request.getCustomerDob()));
                    customers.setAdress(request.getCustomerAddress());
                    return customerRepository.save(customers);
                }).map(customers -> CustomerResponse.builder()
                        .customerId(customers.getId())
                        .phoneNumber(customers.getPhoneNumber())
                        .fullName(customers.getFullname())
                        .email(customers.getEmail())
                        .dob(customers.getDob())
                        .phoneNumber(customers.getPhoneNumber())
                        .address(customers.getAdress())
                        .build());
    }
}
