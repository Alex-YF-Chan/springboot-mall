package com.example.springboot.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class OrderItemsKey implements Serializable {
    @Id
    @Column(name = "order_id")
    private int order_id;
    @Id
    @Column(name = "porduct_id")
    private int proudct_id;
}
