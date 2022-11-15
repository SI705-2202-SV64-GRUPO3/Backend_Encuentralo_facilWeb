package com.encuentralofacil.encuentralofacil.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "stores_products")
@Data
public class StoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "store_id")
    private Long storeId;
    @Column(name = "product_id")
    private Long productId;
    @Column
    private int quantity;
    @Column
    private float price;
}
