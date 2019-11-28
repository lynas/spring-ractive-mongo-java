package com.lynas.reactivespringjava;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@Log4j2
public class CustomerController {
    private final ReactiveCustomerRepository customerRepository;
    private final ReactiveOrderRepository orderRepository;

    @GetMapping
    public Publisher<Customer> getAll() {
        return customerRepository.findAll();
    }

    @PostMapping
    public Publisher<Customer> addNew(@RequestBody Customer customer) {
        Customer saveCustomer = new Customer(customer.firstName, customer.lastName);
        return customerRepository.save(saveCustomer);
    }

    @GetMapping("/orders/{customerId}")
    public Publisher<String> getAllCustomerProducts(@PathVariable String customerId) {
/*
        Mono<Customer> customerMono = customerRepository.findById(customerId);
        Mono<String> customerIdMono = customerMono.map(u -> u.id);
        Flux<Order> orderFlux = orderRepository.findByCustomerId(customerIdMono);
        Flux<String> orderItemFlux = orderFlux.map(order -> order.orderItemName);
*/
        return customerRepository.findById(customerId)
                .flatMapMany(u -> orderRepository.findByCustomerId(Mono.just(u.id)))
                .map(order -> order.orderItemName);
    }

}
