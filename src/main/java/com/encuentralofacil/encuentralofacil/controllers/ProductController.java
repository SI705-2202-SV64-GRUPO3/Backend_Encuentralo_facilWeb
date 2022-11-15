package com.encuentralofacil.encuentralofacil.controllers;

import com.encuentralofacil.encuentralofacil.entities.Product;
import com.encuentralofacil.encuentralofacil.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin
@RequestMapping("/products")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity getAllProducts(@RequestParam(value = "category", required = false) String categoryId) {
        List<Product> products = this.productService.getAllProducts(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("catalogue")
    public ResponseEntity getMatchingProducts(@RequestParam String district) {
        List<Product> products = this.productService.getMatchingProducts(district);

        return ResponseEntity.ok(products);
    }
}
