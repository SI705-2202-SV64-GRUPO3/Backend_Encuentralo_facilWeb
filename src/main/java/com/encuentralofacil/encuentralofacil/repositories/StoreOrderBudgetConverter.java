package com.encuentralofacil.encuentralofacil.repositories;

public interface StoreOrderBudgetConverter {
    Long getStoreId();
    String getStoreName();
    float getTotalPrice();
    Long getProductId();
}
