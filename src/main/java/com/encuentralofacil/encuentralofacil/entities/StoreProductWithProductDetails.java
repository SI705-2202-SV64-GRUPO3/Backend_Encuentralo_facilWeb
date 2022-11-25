package com.encuentralofacil.encuentralofacil.entities;

import lombok.Data;

@Data
public class StoreProductWithProductDetails extends StoreProduct {
    String productName;
    String productImage;
}
