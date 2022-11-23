package com.encuentralofacil.encuentralofacil.controllers;

import com.encuentralofacil.encuentralofacil.entities.CartItem;
import com.encuentralofacil.encuentralofacil.entities.ProductsComparisonBody;
import com.encuentralofacil.encuentralofacil.entities.StoreOrderBudget;
import com.encuentralofacil.encuentralofacil.entities.StoreProduct;
import com.encuentralofacil.encuentralofacil.services.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequestMapping("/stores-products")
@Controller
public class StoreProductController {
    @Autowired
    StoreProductService storeProductService;

    @GetMapping("")
    public ResponseEntity listStoreProducts(@RequestParam(name = "storeId", required = true) Long storeId) {
        List<StoreProduct> storeProducts;

        try {
            storeProducts = this.storeProductService.getAllStoreProductsByStore(storeId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(storeProducts);
    }

    @GetMapping("{id}")
    public ResponseEntity getStoreProductById(@PathVariable("id") Long id) {
        StoreProduct storeProduct = this.storeProductService.getStoreProductById(id).orElse(null);
        if (storeProduct == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(storeProduct);
    }

    @PostMapping("")
    public ResponseEntity createStoreProduct(@RequestBody StoreProduct storeProduct) {
        try {
            this.storeProductService.createStoreProduct(storeProduct);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity updateStoreProduct(@PathVariable("id") Long id, @RequestBody StoreProduct storeProduct) {
        try {
            this.storeProductService.updateStoreProduct(id, storeProduct);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("comparison")
    public ResponseEntity getProductsComparisonByStores(@RequestBody ProductsComparisonBody body) {
        List<StoreOrderBudget> budgets;

        try {
            budgets = this.storeProductService.getProductsComparison(body.getCartItems(), body.getDistrict());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(budgets);
    }
}
