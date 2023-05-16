package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/con")
public class TestController {

    @Autowired
    private NamedParameterJdbcTemplate npjt;
    @GetMapping("/testDb")
    public List<Customer> testDb(){
        String sql ="SELECT id,name FROM customers";
        Map<String,Object> map = new HashMap<>();
        List<Customer> list = npjt.query(sql,map,new CustomerMapper());
        return list;
    }
    @GetMapping("/insert")
    public String insert(){
        String sql = "INSERT INTO customers(id,name,email,password) VALUES('H123456782','testSprings','testsSpringboot22@gmail.com','12345')";
        Map<String,Object> map = new HashMap<>();
        npjt.update(sql,map);
        return "inset成功";
    }
}
