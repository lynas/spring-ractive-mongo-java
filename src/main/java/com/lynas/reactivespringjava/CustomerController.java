package com.lynas.reactivespringjava;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final ReactiveCustomerRepository repository;

    @GetMapping("/customers")
    public Publisher<Customer> getAll() {
        return repository.findAll();
    }

    @PostMapping("/customers")
    public Publisher<Customer> addNew(@RequestBody Customer customer) {
        Customer saveCustomer = new Customer(customer.firstName, customer.lastName);
        return repository.save(saveCustomer);
    }


}
