package com.example.springboot.service;

import com.example.springboot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public interface CustomerService {

    public Customer saveCustomer(Customer customer);
    @Async
    public Customer getById(String id) throws IOException, InterruptedException;

    Page<Customer> findCustomerPage(int startPage);
}
