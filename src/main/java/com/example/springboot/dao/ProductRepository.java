package com.example.springboot.dao;

import com.example.springboot.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


public interface ProductRepository extends CrudRepository<Product,Integer> {
}
