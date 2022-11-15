package com.encuentralofacil.encuentralofacil.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "store_product_id")
    private Long storeProductId;
    @Column(name = "purchase_price")
    private float purchasePrice;
    @Column
    private int quantity;
}
