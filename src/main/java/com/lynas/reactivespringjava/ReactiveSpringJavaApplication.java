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
            Mono<Customer> customer1 = repository.save(new Customer("Mad", "Max"));
            Mono<Customer> customer2 = repository.save(new Customer("Don", "Neto"));
            repository.deleteAll()
                    .thenMany(customer1)
                    .thenMany(customer2)
                    .thenMany(repository.findAll())
                    .subscribe(log::info);
        };
    }

}


