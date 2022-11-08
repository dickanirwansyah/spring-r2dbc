package com.reactive.servicereactive.service;

import com.alibaba.fastjson.JSON;
import com.reactive.servicereactive.base.BaseService;
import com.reactive.servicereactive.entity.Customers;
import com.reactive.servicereactive.model.request.AuditLogRequest;
import com.reactive.servicereactive.model.request.CustomerRequest;
import com.reactive.servicereactive.model.response.CustomerLogResponse;
import com.reactive.servicereactive.repository.CustomerRepository;
import com.reactive.servicereactive.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;



@Slf4j
@Service
public class AddCustomerService implements BaseService<CustomerLogResponse, CustomerRequest> {

    private CustomerRepository customerRepository;
    private AuditLogService auditLogService;
    public AddCustomerService(CustomerRepository customerRepository,
    AuditLogService auditLogService){
        this.customerRepository = customerRepository;
        this.auditLogService = auditLogService;
    }

    @Override
    public Mono<CustomerLogResponse> execute(CustomerRequest customerRequest) {
       return Mono.zip(newCustomer(customerRequest), auditLogService.execute(AuditLogRequest
                .builder()
                        .remarks(JSON.toJSONString(customerRequest))
                .build()))
                .map(objects -> CustomerLogResponse
                        .builder()
                        .id(objects.getT1().getId())
                        .dob(objects.getT1().getDob())
                        .email(objects.getT1().getEmail())
                        .fullname(objects.getT1().getFullname())
                        .adress(objects.getT1().getAdress())
                        .code(objects.getT1().getCode())
                        .phoneNumber(objects.getT1().getPhoneNumber())
                        .remarks(objects.getT2().getRemarks())
                        .build());
    }

    private Mono<Customers> newCustomer(CustomerRequest request){
        Customers customers = Customers
            .builder()
                .code(request.getCustomerCode()) /** UUID **/
                .fullname(request.getCustomerFullName())
                .dob(DateUtil.convertData(request.getCustomerDob()))
                .phoneNumber(request.getCustomerPhoneNumber())
                .adress(request.getCustomerAddress())
                .email(request.getCustomerEmail())
            .build();
         log.info("save customer documents..");
        return this.customerRepository.save(customers);
    }
}
