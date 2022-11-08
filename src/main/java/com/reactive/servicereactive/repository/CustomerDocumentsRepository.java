package com.reactive.servicereactive.repository;

import com.reactive.servicereactive.entity.CustomersDocuments;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerDocumentsRepository extends ReactiveCrudRepository<CustomersDocuments, Long> {

    @Query(value = "select * from customers_documents where customer_code=:customerCode")
    Flux<CustomersDocuments> getCustomerDocumentsByCustomerCode(@Param("customerCode")String customerCode);

}
