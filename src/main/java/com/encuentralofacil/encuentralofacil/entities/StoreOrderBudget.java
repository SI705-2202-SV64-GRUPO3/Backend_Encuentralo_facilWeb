package com.encuentralofacil.encuentralofacil.entities;

import lombok.Data;

import javax.persistence.Column;

@Data
public class StoreOrderBudget {
    @Column(name = "store_id")
    private Long storeId;
    @Column(name = "store_name")
    private String storeName;
    @Column(name = "total_price")
    private float totalPrice;
}
