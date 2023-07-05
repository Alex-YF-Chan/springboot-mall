package com.example.springboot.service.Impl;

import com.example.springboot.dao.CustomerRespository;
import com.example.springboot.entity.Customer;
import com.example.springboot.service.CustomerService;
import com.google.gson.Gson;
import org.hibernate.annotations.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRespository customerDao;
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer getById(String id) throws IOException, InterruptedException {
        HashMap<String,String> map = new HashMap<>();
        map.put("name","morpheus");
        map.put("job","leader");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(map);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users"))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println(response.body());
        return customerDao.findById(id).orElse(null);
    }

    @Override
    public Page<Customer>  findCustomerPage(int startPage) {

        startPage = startPage < 0 ? 0:startPage;
        //1
        //Sort sort =  Sort.by("id").ascending();
        //2
        Sort.TypedSort<Customer> sortType = Sort.sort(Customer.class);
        Sort sort =  sortType.by(Customer::getId).ascending();
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(startPage,5,sort);
        Page<Customer> page = (Page<Customer>) customerDao.findAll(pageable);
        System.out.println("page = "+page.getTotalPages());
        return page;
    }
}
