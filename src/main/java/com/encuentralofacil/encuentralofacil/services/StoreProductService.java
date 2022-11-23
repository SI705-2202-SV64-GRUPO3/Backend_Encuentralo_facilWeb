package com.encuentralofacil.encuentralofacil.services;

import com.encuentralofacil.encuentralofacil.entities.CartItem;
import com.encuentralofacil.encuentralofacil.entities.StoreOrderBudget;
import com.encuentralofacil.encuentralofacil.entities.StoreProduct;
import com.encuentralofacil.encuentralofacil.repositories.StoreOrderBudgetConverter;
import com.encuentralofacil.encuentralofacil.repositories.StoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<StoreOrderBudget> getProductsComparison(List<CartItem> cartItems, String district) {
        Map<Long, Integer> productQuantityMap = new HashMap<>();
        List<Long> productsIds = new ArrayList<>();

        for (CartItem cartItem: cartItems) {
            productsIds.add(cartItem.getProductId());
            productQuantityMap.put(cartItem.getProductId(), cartItem.getQuantity());
        }

        List<StoreOrderBudgetConverter> storeOrderBudgetConverters = this.storeProductRepository.getComparison(productsIds, district);
        return storeOrderBudgetConverters.stream().map(i -> {
            StoreOrderBudget budget = new StoreOrderBudget();
            budget.setStoreName(i.getStoreName());
            budget.setStoreId(i.getStoreId());

            int productQuantity = productQuantityMap.get(i.getProductId());
            budget.setTotalPrice(i.getTotalPrice() * productQuantity);
            return  budget;
        }).collect(Collectors.toList());
    }
}
