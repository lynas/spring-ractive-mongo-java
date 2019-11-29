package com.lynas.reactivespringjava;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ReactiveAuthorRepository extends ReactiveCrudRepository<Author, String> {

    //@Query("{ 'name': ?0}")
    Mono<Author> findByName(String name);
}
