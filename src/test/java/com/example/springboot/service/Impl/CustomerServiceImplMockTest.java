package com.example.springboot.service.Impl;

import com.example.springboot.dao.CustomerRespository;
import com.example.springboot.entity.Customer;
import com.example.springboot.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class CustomerServiceImplMockTest {

    @Autowired
    private CustomerService customerService;

    //製造假bean
    @MockBean
    private CustomerRespository customerDao;

    @Test
    public void getById() throws IOException, InterruptedException {
        Customer mockCustomer = new Customer();
        mockCustomer.setId("A123456789");
        mockCustomer.setName("I am Mock");
        Mockito.when(customerDao.findById(Mockito.any())).thenReturn(java.util.Optional.of(mockCustomer));
//        Mockito.when(customerDao.findById("A123456789")).thenReturn(java.util.Optional.of(mockCustomer));
        Customer customer = customerService.getById("A123456789");
        assertEquals("I am Mock",customer.getName());
    }
}