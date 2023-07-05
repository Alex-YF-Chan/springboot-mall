package com.example.springboot.dao;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRespository extends JpaRepository<Orders,Integer> {
    List<Orders> findByCustomer(Customer customer);

//    List<Orders> findByCustomer(Customer customer);
}
