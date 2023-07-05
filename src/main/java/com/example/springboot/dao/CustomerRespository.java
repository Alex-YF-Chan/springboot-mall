package com.example.springboot.dao;

import com.example.springboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerRespository extends JpaRepository<Customer,String> {
    List<Customer> findByName(String name);

    @Query(value = "SELECT * FROM customers WHERE id = :id and name = :name",nativeQuery = true)
    Customer customerNativeSql(@Param("id")String id, @Param("name")String name);
    @Query("FROM Customer where name = ?1")
    Customer findbyName(String name);
    @Modifying//JPQL 一定要加上
    @Query("UPDATE Customer c set c.name = ?1 where c.id = ?2")
    int updateCustomerName(String name,String id);
}
