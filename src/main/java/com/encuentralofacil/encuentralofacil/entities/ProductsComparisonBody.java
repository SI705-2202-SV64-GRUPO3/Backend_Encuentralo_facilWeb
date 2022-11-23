package com.encuentralofacil.encuentralofacil.entities;

import lombok.Data;

import java.util.List;

@Data
public class ProductsComparisonBody {
    private List<CartItem> cartItems;
    private String district;
}
