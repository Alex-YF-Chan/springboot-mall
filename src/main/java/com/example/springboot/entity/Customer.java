package com.example.springboot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name="customers")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    private String id;
    @NotNull
    @Column(name="name")
    private String name;
    @NotNull
    @Column(name="email")
    @NotNull
    private String email;
    @Column(name="password")
    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private List<Orders> ordersList;
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")//message的外建
    private List<Message> message;
}
