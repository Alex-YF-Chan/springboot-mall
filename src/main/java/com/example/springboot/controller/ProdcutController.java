package com.example.springboot.controller;

import com.example.springboot.dao.OrderItemRespository;
import com.example.springboot.dao.OrderRespository;
import com.example.springboot.entity.*;
import com.example.springboot.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProdcutController {
    Logger log = LogManager.getLogger(ProdcutController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemRespository orderItemRespository;
    @Autowired
    private OrderRespository orderRespository;

    @GetMapping("/findById/{id}")
    public String findProductById(@PathVariable int id){
        System.out.println("id = "+id);
        Optional<Product> p =productService.findById(id);
        Product realP = p.get();
        log.info(realP.getName());
        return "查詢productById";
    };

    @GetMapping(value = "/findOrderItem/{id}")
    public String findByOrderItemsById(@PathVariable int id){
        OrderItemsKey key = new OrderItemsKey();
        key.setOrder_id(2);
        key.setProudct_id(1);
       Optional<OrderItems> o =  orderItemRespository.findById(key);
       System.out.println( o.get().getColor_name());
//       o.get().getProudct_id();
        return "查詢OrerItem";
    }
//    @GetMapping(value = "/findOrderbyCustomer")
//    public ResponseEntity<List<Orders>> findOrders(@RequestParam(name = "customerId") String customerId){
//        Customer customer = new Customer();
//        customer.setId(customerId);
//        List<Orders> list = orderRespository.findByCustomer(customer);
//        return ResponseEntity.ok().body(list);
//    }

    @GetMapping(value = "/findOrderbyCustomerId")
    public ResponseEntity<List<Orders>> findOrdersById(@RequestParam(name = "customerId") String customerId){
        Customer c = new Customer();
        c.setId(customerId);
        List<Orders> list = orderRespository.findByCustomer(c);
        return ResponseEntity.ok().body(list);
    }

    public void queryCustomerDsl(){

    }
}
