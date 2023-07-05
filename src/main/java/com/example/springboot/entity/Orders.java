package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_id",insertable=false, updatable = false)
    private String customerId;
    @Column(name = "order_date")
    private Date order_date;
    @Column(name = "recipient_name")
    private String recipientName;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="customer_id",insertable=false, updatable = false)
//    @JoinTable(name = "customers")
    @JsonIgnore
    private Customer customer;
    @OneToMany
    @JoinColumn(name = "order_id")
    List<OrderItems> orderItemsList;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", order_date=" + order_date +
                ", recipientName='" + recipientName + '\'' +
                ", customerName=" + customer.getName() +
                ", orderItemsList=" + orderItemsList +
                '}';
    }
}
