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
    CommandLineRunner init(ReactiveCustomerRepository repository) {
        return args -> {
            Mono<Customer> save1 = repository.save(new Customer("1", "Sazzad", "Islam"));
            Mono<Customer> save2 = repository.save(new Customer("2", "Suraiya", "Lopa"));
            repository.deleteAll()
                    .thenMany(save1)
                    .thenMany(save2)
                    .thenMany(repository.findAll())
                    .subscribe(log::info);

        };
    }

}
