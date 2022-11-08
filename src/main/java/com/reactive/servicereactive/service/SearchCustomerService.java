package com.reactive.servicereactive.service;

import com.reactive.servicereactive.entity.Customers;
import com.reactive.servicereactive.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SearchCustomerService {

    private CustomerRepository customerRepository;
    public SearchCustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Mono<Page<Customers>> getDataCustomers(Pageable pageable){
        return this
                .customerRepository.findAllBy(pageable)
                .collectList()
                .zipWith(this.customerRepository.count())
                .map(data -> new PageImpl<>(data.getT1(), pageable, data.getT2()));
    }
}
