package com.reactive.servicereactive.controller;

import com.reactive.servicereactive.entity.Customers;
import com.reactive.servicereactive.model.request.CustomerCustomDocumentRequest;
import com.reactive.servicereactive.model.request.CustomerDocumentListRequest;
import com.reactive.servicereactive.model.request.CustomerRequest;
import com.reactive.servicereactive.model.response.CustomerCustomDocumentResponse;
import com.reactive.servicereactive.model.response.CustomerDocumentResponse;
import com.reactive.servicereactive.model.response.CustomerLogResponse;
import com.reactive.servicereactive.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    private SearchCustomerService searchCustomerService;
    private AddCustomerService addCustomerService;
    private AddCustomerDocumentService addCustomerDocumentService;
    private AddDetailCustomerDocumentService addDetailCustomerDocumentService;
    private UpdateCustomerService updateCustomerService;
    private UpdateCustomerDocumentService updateCustomerDocumentService;
    public CustomerController(SearchCustomerService searchCustomerService,
                              AddCustomerService addCustomerService,
                              AddCustomerDocumentService addCustomerDocumentService,
                              AddDetailCustomerDocumentService addDetailCustomerDocumentService,
                              UpdateCustomerService updateCustomerService,
                              UpdateCustomerDocumentService updateCustomerDocumentService){
        this.searchCustomerService = searchCustomerService;
        this.addCustomerService = addCustomerService;
        this.addCustomerDocumentService = addCustomerDocumentService;
        this.addDetailCustomerDocumentService = addDetailCustomerDocumentService;
        this.updateCustomerService = updateCustomerService;
        this.updateCustomerDocumentService = updateCustomerDocumentService;
    }

    @GetMapping(value = "/v1/list")
    public Mono<Page<Customers>> getList(@RequestParam("page")int page,
                                         @RequestParam("size")int size){
        return this.searchCustomerService.getDataCustomers(PageRequest.of(page, size));
    }

    @PostMapping(value = "/v1/save")
    public Mono<CustomerLogResponse> saveCustomer(@RequestBody CustomerRequest request){
        return this.addCustomerService.execute(request);
    }

    @PostMapping(value = "/v1/save-customer-documents")
    public Mono<CustomerDocumentResponse> saveCustomerDocuments(@RequestBody CustomerDocumentListRequest request){
        return this.addCustomerDocumentService.execute(request);
    }

    @PostMapping(value = "/v1/save-customer-detail")
    public Mono<CustomerCustomDocumentResponse> saveDetailCustomerDocuments(@RequestBody CustomerCustomDocumentRequest request){
        return this.addDetailCustomerDocumentService.execute(request);
    }

    @PutMapping(value = "/v1/update-customer-log")
    public Mono<CustomerLogResponse> updateCustomerLog(@RequestBody CustomerRequest request){
        return this.updateCustomerService.execute(request);
    }

    @PutMapping(value = "/v1/update-customer-documents")
    public Mono<CustomerDocumentResponse> updateCustomerDocuments(@RequestBody CustomerDocumentListRequest request){
        return this.updateCustomerDocumentService.execute(request);
    }
}
