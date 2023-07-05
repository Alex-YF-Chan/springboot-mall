package com.example.springboot.controller;



import com.example.springboot.dao.CustomerRespository;
import com.example.springboot.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerRespository customerRespository;
    @Test
    public void getByName() throws Exception {
        //設定url及參數
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/findByName/{name}","王陸");
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id",equalTo("A123456789")))
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        System.out.println(body);
    }

    @Transactional//測完會rollback
    @Test
    public void create() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/createAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\":\"A123987234\",\n" +
                        "    \"email\":\"testMockMvc@gmail.com\",\n" +
                        "    \"name\":\"MockMvc\",\n" +
                        "    \"password\":\"password\"\n" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));

    }
    @Test
    public void findById(){
        Optional<Customer> c = customerRespository.findById("A123456789");
        System.out.println(c.get());
    }
}