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
    public String getAllCustomerProducts(@PathVariable String customerId) {


        demo(Mono.just(customerId));

        return "Ok";

    }

    private void demo(Mono<String> customerId){
        customerRepository.findById(customerId)
                .thenMany(orderRepository.findByCustomerId(customerId))
                .subscribe(log::info);
    }


}
