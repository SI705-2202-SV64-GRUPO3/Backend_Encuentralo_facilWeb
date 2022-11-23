package com.encuentralofacil.encuentralofacil.services;

import com.encuentralofacil.encuentralofacil.entities.Product;
import com.encuentralofacil.encuentralofacil.entities.Store;
import com.encuentralofacil.encuentralofacil.repositories.ProductRepository;
import com.encuentralofacil.encuentralofacil.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;

    public List<Product> getMatchingProducts(String district, String categoryId) {
        List<Store> storesInDistrict = this.storeRepository.findStoresByDistrict(district);
        List<Long> storeIds = new ArrayList<>();

        if (storesInDistrict.size() == 0) return new ArrayList<Product>();

        storesInDistrict.stream().forEach((s) -> {
            storeIds.add(s.getId());
        });

        List<Product> allProducts = this.productRepository.findInStoreIds(storeIds);
        System.out.println(allProducts);

        Map<Long, Integer> productMapCount  = new HashMap<>();
        allProducts.stream().forEach((product -> {
            if (productMapCount.containsKey(product.getId())) {
                Integer count = productMapCount.get(product.getId());
                productMapCount.put(product.getId(), count + 1);
            } else {
                productMapCount.put(product.getId(), 1);
            }
        }));

        List<Long> matchingProductsIds = new ArrayList<>();
        for (Long key: productMapCount.keySet()) {
            Integer count = productMapCount.get(key);
            if (count == storeIds.size()) {
                matchingProductsIds.add(key);
            }
        }

        List<Product> matchingProducts;
        if (categoryId == null) {
            matchingProducts = this.productRepository.findInIds(matchingProductsIds);
        } else {
            matchingProducts = this.productRepository.findInIdsAndCategory(matchingProductsIds, categoryId);
        }

        return matchingProducts;
    }

    public List<Product> getAllProducts(String categoryId) {
        if (categoryId == null) {
            return this.productRepository.findAll();
        }

        return this.productRepository.findByCategory(categoryId);
    }
}
