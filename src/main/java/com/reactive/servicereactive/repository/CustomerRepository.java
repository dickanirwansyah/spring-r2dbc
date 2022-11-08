package com.reactive.servicereactive.repository;

import com.reactive.servicereactive.entity.Customers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customers, Long>{

    @Query("select * from customers offset :offset limit :limit")
    Flux<Customers> getCustomers(Pageable pageable, int offset, int limit);

    Flux<Customers> findAllBy(Pageable pageable);
}
