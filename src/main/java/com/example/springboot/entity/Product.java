package com.example.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "discount")
    private int discount;
    @Column(name = "stock")
    private int stock;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "shelfDate")
    private Date shelfDate;
    @Column(name = "category")
    private String category;
    @Column(name = "brand")
    private String brand;

}
