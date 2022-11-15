package com.encuentralofacil.encuentralofacil.services;

import com.encuentralofacil.encuentralofacil.entities.Product;
import com.encuentralofacil.encuentralofacil.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getMatchingProducts(String district) {
        return this.productRepository.getCommonProductsInStoresByDistrict(district);
    }

    public List<Product> getAllProducts(String categoryId) {
        if (categoryId == null) {
            return this.productRepository.findAll();
        }

        return this.productRepository.findByCategory(categoryId);
    }
}
