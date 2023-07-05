package com.example.springboot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="customers")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private List<Orders> ordersList;
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")//message的外建
    private List<Message> message;
}
