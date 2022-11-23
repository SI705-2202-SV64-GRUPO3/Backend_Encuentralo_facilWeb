package com.encuentralofacil.encuentralofacil.entities;

import lombok.Data;

@Data
public class OrderWithTotal extends Order {
    private float total;
}
