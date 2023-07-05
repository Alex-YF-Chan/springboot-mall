package com.example.springboot.dao;

import com.example.springboot.entity.OrderItems;
import com.example.springboot.entity.OrderItemsKey;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRespository extends CrudRepository<OrderItems, OrderItemsKey> {

}
