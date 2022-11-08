package com.reactive.servicereactive.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.reactive.servicereactive.base.BaseService;
import com.reactive.servicereactive.entity.CustomersDocuments;
import com.reactive.servicereactive.model.request.CustomerDocumentListRequest;
import com.reactive.servicereactive.model.response.CustomerDocumentResponse;
import com.reactive.servicereactive.repository.CustomerDocumentsRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AddCustomerDocumentService implements BaseService<CustomerDocumentResponse, CustomerDocumentListRequest>{

    private CustomerDocumentsRepository customerDocumentsRepository;
    public AddCustomerDocumentService(CustomerDocumentsRepository customerDocumentsRepository){
        this.customerDocumentsRepository = customerDocumentsRepository;
    }

    @Override
    public Mono<CustomerDocumentResponse> execute(CustomerDocumentListRequest request) {
        log.info("request document");
        return Mono.just(CustomerDocumentResponse
        .builder()
            .customerDocuments(doUpload(request))
        .build());
    }

    private List<CustomersDocuments> doUpload(CustomerDocumentListRequest request){
        List<CustomersDocuments> listDocuments = new ArrayList<>();
        request.getCustomerDocumentList().stream().forEach(data -> {
            log.info("document type -> {} ",data.getCustomerDocumentType());
            CustomersDocuments customersDocuments = new CustomersDocuments();
            customersDocuments.setCode(UUID.randomUUID().toString());
            customersDocuments.setCustomerCode(data.getCustomerId());
            customersDocuments.setDocumentFile(data.getCustomerDocumentFile());
            customersDocuments.setDocumentType(data.getCustomerDocumentType());
            customersDocuments.setDocumentName(data.getCustomerDocumentName());
            listDocuments.add(customersDocuments);
        });
        return this.customerDocumentsRepository.saveAll(listDocuments)
            .toStream().map(data -> CustomersDocuments
                .builder()
                .id(data.getId())
                .code(data.getCode())
                .customerCode(data.getCustomerCode())
                .documentFile(data.getDocumentFile())
                .documentName(data.getDocumentName())
                .documentType(data.getDocumentType())
                .build())
                .collect(Collectors.toList());
    }
}
