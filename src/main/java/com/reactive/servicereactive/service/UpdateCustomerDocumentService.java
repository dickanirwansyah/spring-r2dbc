package com.reactive.servicereactive.service;

import com.reactive.servicereactive.base.BaseService;
import com.reactive.servicereactive.entity.CustomersDocuments;
import com.reactive.servicereactive.model.request.CustomerDocumentListRequest;
import com.reactive.servicereactive.model.response.CustomerDocumentResponse;
import com.reactive.servicereactive.repository.CustomerDocumentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UpdateCustomerDocumentService implements BaseService<CustomerDocumentResponse, CustomerDocumentListRequest> {

    @Autowired
    private CustomerDocumentsRepository customerDocumentsRepository;
    @Autowired
    private AddCustomerDocumentService addCustomerDocumentService;

    @Override
    public Mono<CustomerDocumentResponse> execute(CustomerDocumentListRequest request) {
       log.info("request update document by customer -> {} ",request.getCustomerDocumentList().get(0).getCustomerId());
       /** delete document existing **/
       return Mono.zip(
               doDestroyDocumentExisting(request.getCustomerDocumentList().get(0).getCustomerId()),
               addCustomerDocumentService.execute(request))
               .map(objectsTuple -> CustomerDocumentResponse
                       .builder()
                       .customerDocuments(objectsTuple.getT2().getCustomerDocuments()
                               .stream().map(customersDocuments -> CustomersDocuments.builder()
                                       .id(customersDocuments.getId())
                                       .code(customersDocuments.getCode())
                                       .customerCode(customersDocuments.getCustomerCode())
                                       .documentType(customersDocuments.getDocumentType())
                                       .documentName(customersDocuments.getDocumentName())
                                       .documentFile(customersDocuments.getDocumentFile())
                                       .build()).collect(Collectors.toList()))
                       .build());
    }

    private Mono<Void> doDestroyDocumentExisting(String customerId){
        log.info("request delete document by customer -> {} ",customerId);
        List<CustomersDocuments> customersDocumentsList = new ArrayList<>();
        this.customerDocumentsRepository.getCustomerDocumentsByCustomerCode(customerId)
                .toStream()
                .forEach(customersDocuments -> customersDocumentsList.add(customersDocuments));
        return this.customerDocumentsRepository.deleteAll(customersDocumentsList);
    }
}
