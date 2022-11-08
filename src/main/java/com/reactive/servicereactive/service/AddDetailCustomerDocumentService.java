package com.reactive.servicereactive.service;

import com.reactive.servicereactive.base.BaseService;
import com.reactive.servicereactive.model.request.CustomerCustomDocumentRequest;
import com.reactive.servicereactive.model.request.CustomerDocumentListRequest;
import com.reactive.servicereactive.model.request.CustomerDocumentRequest;
import com.reactive.servicereactive.model.request.CustomerRequest;
import com.reactive.servicereactive.model.response.CustomerCustomDocumentResponse;
import com.reactive.servicereactive.model.response.CustomerDocumentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddDetailCustomerDocumentService implements BaseService<CustomerCustomDocumentResponse,
        CustomerCustomDocumentRequest> {

    @Autowired
    private AddCustomerService addCustomerService;

    @Autowired
    private AddCustomerDocumentService addCustomerDocumentService;

    @Override
    public Mono<CustomerCustomDocumentResponse> execute(CustomerCustomDocumentRequest request) {
        log.info("add detail customer document..");
        String customerCode = this.generateCustomerCode();
        log.info("generate customer code -> {} ",customerCode);
        return Mono.zip(addCustomerService.execute(CustomerRequest
                                .builder()
                                .customerCode(customerCode)
                                .customerEmail(request.getCustomerEmail())
                                .customerDob(request.getCustomerDob())
                                .customerAddress(request.getCustomerAddress())
                                .customerFullName(request.getCustomerFullName())
                                .customerPhoneNumber(request.getCustomerPhoneNumber())
                                .build()),
                addCustomerDocumentService.execute(CustomerDocumentListRequest
                        .builder()
                                .customerDocumentList(request.getCustomerDocumentRequests().stream()
                                        .map(custDoc -> CustomerDocumentRequest.builder()
                                                .customerId(customerCode)
                                                .customerDocumentName(custDoc.getCustomerDocumentName())
                                                .customerDocumentType(custDoc.getCustomerDocumentType())
                                                .customerDocumentFile(custDoc.getCustomerDocumentFile())
                                                .build()).collect(Collectors.toList()))
                        .build()))
                .map(objectTuple -> CustomerCustomDocumentResponse
                        .builder()
                        .id(objectTuple.getT1().getId())
                        .code(objectTuple.getT1().getCode())
                        .dob(objectTuple.getT1().getDob())
                        .adress(objectTuple.getT1().getAdress())
                        .phoneNumber(objectTuple.getT1().getPhoneNumber())
                        .fullname(objectTuple.getT1().getFullname())
                        .email(objectTuple.getT1().getEmail())
                        .customerDocumentResponses(objectTuple.getT2().getCustomerDocuments()
                                .stream().map(customersDocuments -> CustomerDocumentResponse
                                        .builder()
                                        .customerDocuments(objectTuple.getT2().getCustomerDocuments())
                                        .build())
                                .collect(Collectors.toList()))
                        .build());
    }

    private String generateCustomerCode(){
        return UUID.randomUUID().toString();
    }
}
