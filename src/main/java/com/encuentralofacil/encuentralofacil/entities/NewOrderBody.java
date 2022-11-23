package com.encuentralofacil.encuentralofacil.entities;

import lombok.Data;

import java.util.List;

@Data
public class NewOrderBody {
    List<CartItem> cartItems;
    Long clientId;
    Long storeId;
}
