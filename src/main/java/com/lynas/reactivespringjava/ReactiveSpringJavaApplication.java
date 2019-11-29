package com.lynas.reactivespringjava;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Log4j2
public class ReactiveSpringJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpringJavaApplication.class, args);
    }

    @Bean
    public LoggingEventListener mongoEventListener() {
        return new LoggingEventListener(); // logging mongodb operations
    }

    @Bean
    CommandLineRunner init(ReactiveBookRepository bookRepository, ReactiveAuthorRepository authorRepository) {
        return args -> {
            Mono<Author> author1 = authorRepository.save(new Author("1", "Max"));
            Mono<Author> author2 = authorRepository.save(new Author("2", "Don"));
            Mono<Book> book1 = bookRepository.save(new Book("Java", "1"));
            Mono<Book> book2 = bookRepository.save(new Book("Spring", "1"));
            Mono<Book> book3 = bookRepository.save(new Book("Swift", "2"));
            authorRepository.deleteAll()
                    .thenMany(bookRepository.deleteAll())
                    .thenMany(author1)
                    .thenMany(author2)
                    .thenMany(book1)
                    .thenMany(book2)
                    .thenMany(book3)
                    .thenMany(bookRepository.findAll())
                    .thenMany(authorRepository.findAll())
                    .subscribe(log::info);
        };
    }

}


