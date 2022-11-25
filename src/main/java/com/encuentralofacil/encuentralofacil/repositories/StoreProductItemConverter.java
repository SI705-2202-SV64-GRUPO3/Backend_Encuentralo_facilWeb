package com.encuentralofacil.encuentralofacil.repositories;

public interface StoreProductItemConverter {
    Long getId();
    String getProductName();
    String getProductImage();
    int getQuantity();
    float getPrice();
}
