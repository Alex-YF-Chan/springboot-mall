package com.example.springboot.controller;

import com.example.springboot.entity.Customer;
import com.example.springboot.dao.CustomerRespository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    Logger log = LogManager.getLogger(CustomerController.class);
    @Autowired
    private CustomerRespository customerRespository;

    @PostMapping("/createAccount")
    public String insert(@RequestBody Customer customer){
        customerRespository.save(customer);
        return "執行建立帳號";
    }

    @Transactional
    @Modifying(clearAutomatically = true)
    @PutMapping("/updateAccount/{customerId}")
    public String update(@PathVariable String customerId,
                         @RequestBody Customer customer){
        log.info("測試更新");
        Customer c = customerRespository.findById(customerId).orElse(null);

        if(c!=null){
            customer.setId(customerId);
            customerRespository.save(customer);
            return "更新OK";
//            throw new RuntimeException();
        }else{
            return "沒有帳號";
        }
    }

    @DeleteMapping("/deleteAccount/{customerId}")
    public String delete(@PathVariable String customerId ){
        Customer c = new Customer();
        c.setId(customerId);
        customerRespository.delete(c);
        return "刪除帳號成功";
    }

    @GetMapping("/find/{customerId}")
    public Customer read(@PathVariable String customerId){
        log.info("測試查詢");
        Customer customer = customerRespository.findById(customerId).orElse(null);
        return customer;
    }

    @GetMapping("/findByName/{name}")
    public List<Customer> findByName(@PathVariable String name){
        List<Customer> customerList = customerRespository.findByName(name);
        return customerList;
    }

    @GetMapping("/findByNativeSql")
    public Customer findNativeSql(@RequestParam String id, @RequestParam String name){
        Customer c = customerRespository.customerNativeSql(id,name);
        return c;
    }
}
