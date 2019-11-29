package com.lynas.reactivespringjava;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReactiveBookRepository extends ReactiveCrudRepository<Book, String> {

    Flux<Book> findByAuthorId(String authorId);
}
