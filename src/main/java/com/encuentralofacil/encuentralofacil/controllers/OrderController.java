package com.encuentralofacil.encuentralofacil.controllers;

import com.encuentralofacil.encuentralofacil.entities.NewOrderBody;
import com.encuentralofacil.encuentralofacil.entities.OrderWithTotal;
import com.encuentralofacil.encuentralofacil.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/orders")
@CrossOrigin
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity createOrder(@RequestBody NewOrderBody order) {
        try {
            this.orderService.createOrder(order.getCartItems(), order.getStoreId(), order.getClientId());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity getOrdersHistory(@RequestParam(value = "clientId", required = true) Long clientId) {
        List<OrderWithTotal> orders;

        try {
            orders = this.orderService.getOrderHistory(clientId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(orders);
    }
}
