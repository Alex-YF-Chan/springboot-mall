package com.example.springboot.dao;

import com.example.springboot.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class CustomerRespositoryTest {

    @Autowired
    private CustomerRespository cDao;

    @Test
    public void findByName(){
       Optional<Customer> c = cDao.findById("A123456789");
//       c.map(p->p.getName());
//        assertNotNull(c);
        assertEquals("王陸",c.get().getName());
    }

}