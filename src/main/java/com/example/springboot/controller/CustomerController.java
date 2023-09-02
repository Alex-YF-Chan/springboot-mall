package com.example.springboot.controller;

import com.example.springboot.dao.CustomerQBExampleRespository;
import com.example.springboot.dao.MessageRespository;
import com.example.springboot.dao.OrderRespository;
import com.example.springboot.entity.Customer;
import com.example.springboot.dao.CustomerRespository;
import com.example.springboot.entity.Message;
import com.example.springboot.entity.Orders;
import com.example.springboot.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Validated // 因為有加上max 和 min,要加上才會生效
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" })
public class CustomerController {
    Logger log = LogManager.getLogger(CustomerController.class);
    @Autowired
    private CustomerRespository customerRespository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderRespository orderRespository;
    @Autowired
    private CustomerQBExampleRespository customerQBExampleRespository;
    @Autowired
    private MessageRespository messageRespository;

    @PostMapping("/createAccount")
    public ResponseEntity<Customer> insert(@RequestBody Customer customer) {
        Customer c = customerRespository.customerNativeSql(customer.getId(), customer.getName());
        if (c == null) {
            c = customerService.saveCustomer(customer);
            // customerRespository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(c);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Customer());
        }

    }

    // @Transactional
    @PutMapping("/updateAccount/{customerId}")
    public String update(@PathVariable String customerId,
            @RequestBody @Valid Customer customer) {
        log.info("測試更新");
        Customer c = customerRespository.findById(customerId).orElse(null);

        if (c != null) {
            Orders orders = new Orders();
            Date now = new Date();
            orders.setOrder_date(now);
            orders.setCustomerId(customerId);
            orders.setRecipientName("測試update join JPA");
            customer.setId(customerId);
            orderRespository.save(orders);

            customerRespository.save(customer);
            return "更新OK";
            // throw new RuntimeException();
        } else {
            return "沒有帳號";
        }
    }

    @DeleteMapping("/deleteAccount/{customerId}")
    public String delete(@PathVariable String customerId) {
        Customer c = new Customer();
        c.setId(customerId);
        customerRespository.delete(c);
        return "刪除帳號成功";
    }

    @GetMapping("/find/{customerId}")
    public Customer read(@PathVariable String customerId) throws IOException, InterruptedException {
        log.info("測試查詢");
        // Optional<Customer> c = customerRespository.findById(customerId);
        Customer customer = customerService.getById(customerId);
        // System.out.println(c.get().getMessage());
        return customer;
    }

    @GetMapping("/findByName/{name}")
    public List<Customer> findByName(@PathVariable String name) {
        List<Customer> customerList = customerRespository.findByName(name);
        return customerList;
    }

    @GetMapping("/findByNativeSql")
    public Customer findNativeSql(@RequestParam String id, @RequestParam String name) {
        Customer c = customerRespository.customerNativeSql(id, name);
        return c;
    }

    @GetMapping("/findCustomerPage")
    public ResponseEntity<Page<Customer>> findCustomerPage(@RequestParam int startPage) {
        Page<Customer> list = customerService.findCustomerPage(startPage);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/findbyName")
    public ResponseEntity<Customer> findbyName(@RequestParam String name) {
        Customer c = customerRespository.findbyName(name);
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/updateCustomerName")
    @Transactional // JPQL 要加上交易管理
    public int updateCustomerName(@RequestParam String name, @RequestParam String id) {
        int update = customerRespository.updateCustomerName(name, id);
        return update;
    }

    @GetMapping("/queryByExample")
    public String queryByExample() {

        Customer sC = new Customer();
        sC.setName("測試");
        // sC.setEmail("test0091703@gmail.com");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("email");
        // 構建查詢
        Example<Customer> ex = Example.of(sC);
        List<Customer> c = (List<Customer>) customerQBExampleRespository.findAll(ex);
        System.out.println(c);
        return "queryByExample";
    }

    @GetMapping("/findMessageByUserId")
    public String findMessageByUserId(@RequestParam String id,
            @RequestParam(defaultValue = "date", required = false) String orderBy,
            @RequestParam(defaultValue = "desc", required = false) String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset) {
        // 加上了一堆分業查詢的request,練習用
        List<Message> list = messageRespository.findByCustomerId(id);
        // Optional<Message> m =messageRespository.findById(id);
        return list.toString();
    }
}
