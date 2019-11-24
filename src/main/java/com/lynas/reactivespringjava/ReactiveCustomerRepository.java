package com.lynas.reactivespringjava;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveCustomerRepository extends ReactiveCrudRepository<Customer, String> {
    Flux<Customer> findByLastName(Mono<String> lastname);

    @Query("{ 'firstName': ?0, 'lastName': ?1}")
    Mono<Customer> findByFirstNameAndLastName(String firstName, String lastName);


}
