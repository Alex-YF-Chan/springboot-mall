package com.example.springboot.service;

import com.example.springboot.entity.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ProductService {

    public Optional<Product> findById(Integer id);
}
