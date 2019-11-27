package com.lynas.reactivespringjava;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveOrderRepository extends ReactiveCrudRepository<Order, String> {
    Flux<Order> findByCustomerId(Mono<String> customerId);

}
