package com.example.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@IdClass(OrderItemsKey.class)
public class OrderItems {
    @Id
    @Column(name = "order_id")
    private int order_id;
    @Id
    @Column(name = "porduct_id")
    private int proudct_id;
    @Id
    @Column(name = "color_name")
    private String color_name;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private int quantity;

}
