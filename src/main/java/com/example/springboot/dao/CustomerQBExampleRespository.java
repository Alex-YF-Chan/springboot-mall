package com.example.springboot.dao;

import com.example.springboot.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CustomerQBExampleRespository extends PagingAndSortingRepository<Customer,String>, QueryByExampleExecutor<Customer> {


}
