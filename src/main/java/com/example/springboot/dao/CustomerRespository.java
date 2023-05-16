package com.example.springboot.dao;

import com.example.springboot.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRespository extends CrudRepository<Customer,String> {
    List<Customer> findByName(String name);

    @Query(value = "SELECT * FROM customers WHERE id = ?1 and name = ?2",nativeQuery = true)
    Customer customerNativeSql(String id,String name);
}
