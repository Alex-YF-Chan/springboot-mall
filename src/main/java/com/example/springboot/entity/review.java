package com.example.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "reviews")
public class review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "content")
    private String content;
    @Column(name = "comment_date")
    private Date commentDate;
}
