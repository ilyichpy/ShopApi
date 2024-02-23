package com.example.shopapi.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "available_stock")
    private long availableStock;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client supplier;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Images imageID;

}
