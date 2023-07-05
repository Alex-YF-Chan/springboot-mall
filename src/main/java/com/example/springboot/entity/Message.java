package com.example.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "customer_id",insertable=false, updatable = false)
    private String customerId;
    @Column(name = "message")
    private String message;
//    @ManyToOne
//    @JoinColumn(name = "id",insertable=false, updatable = false)
//    private Customer customer;
}
