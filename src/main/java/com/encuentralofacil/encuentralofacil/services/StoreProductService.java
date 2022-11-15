package com.encuentralofacil.encuentralofacil.services;

import com.encuentralofacil.encuentralofacil.entities.StoreProduct;
import com.encuentralofacil.encuentralofacil.repositories.StoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreProductService {
    @Autowired
    private StoreProductRepository storeProductRepository;

    public void createStoreProduct(StoreProduct storeProduct) {
        this.storeProductRepository.save(storeProduct);
    }

    public void updateStoreProduct(Long id, StoreProduct storeProduct) {
        storeProduct.setId(id);
        this.storeProductRepository.save(storeProduct);
    }

    public List<StoreProduct> getAllStoreProductsByStore(Long storeId) {
        return this.storeProductRepository.findByStoreId(storeId);
    }

    public Optional<StoreProduct> getStoreProductById(Long id) {
        return this.storeProductRepository.findById(id);
    }
}
