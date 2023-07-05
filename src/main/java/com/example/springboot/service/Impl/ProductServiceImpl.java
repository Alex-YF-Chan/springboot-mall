package com.example.springboot.service.Impl;

import com.example.springboot.dao.ProductRepository;
import com.example.springboot.entity.Product;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
}
