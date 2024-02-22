package com.example.shopapi.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String category;

    @Column(name = "available_stock")
    private long availableStock;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "suplier_id")
    @ManyToOne
    @JoinColumn(name = "client.id")
    private UUID suplierId;
    // Товар
//    product
//    {
//        id
//                name
//        category
//                price
//        available_stock // число закупленных экземпляров товара
//                last_update_date // число последней закупки
//        supplier_id
//        image_id: UUID
//    }
}
