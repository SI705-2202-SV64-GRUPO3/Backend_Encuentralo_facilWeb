package com.encuentralofacil.encuentralofacil.services;

import com.encuentralofacil.encuentralofacil.entities.*;
import com.encuentralofacil.encuentralofacil.repositories.StoreOrderBudgetConverter;
import com.encuentralofacil.encuentralofacil.repositories.StoreProductRepository;
import com.encuentralofacil.encuentralofacil.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreProductService {
    @Autowired
    private StoreProductRepository storeProductRepository;
    @Autowired
    private StoreRepository storeRepository;

    public void createStoreProduct(StoreProduct storeProduct) {
        this.storeProductRepository.save(storeProduct);
    }

    public void updateStoreProduct(Long id, StoreProduct storeProduct) {
        storeProduct.setId(id);
        this.storeProductRepository.save(storeProduct);
    }

    public List<StoreProductWithProductDetails> getAllStoreProductsByStore(Long storeId) {
        List<StoreProductWithProductDetails> storeProducts = this.storeProductRepository.findByStoreId(storeId).stream().map(item -> {
            StoreProductWithProductDetails storeProduct = new StoreProductWithProductDetails();
            storeProduct.setProductName(item.getProductName());
            storeProduct.setProductImage(item.getProductImage());
            storeProduct.setQuantity(item.getQuantity());
            storeProduct.setPrice(item.getPrice());
            storeProduct.setId(item.getId());

            return storeProduct;
        }).collect(Collectors.toList());

        return storeProducts;
    }

    public Optional<StoreProduct> getStoreProductById(Long id) {
        return this.storeProductRepository.findById(id);
    }

    public List<StoreOrderBudget> getProductsComparison(List<CartItem> cartItems, String district) {
        Map<Long, Integer> productQuantityMap = new HashMap<>();
        List<Long> productsIds = new ArrayList<>();

        List<StoreOrderBudget> budgets = new ArrayList<>();

        for (CartItem cartItem: cartItems) {
            productsIds.add(cartItem.getProductId());
            productQuantityMap.put(cartItem.getProductId(), cartItem.getQuantity());
        }

        List<Store> stores = this.storeRepository.findStoresByDistrict(district);
        for (Store store: stores) {
            float total = 0;
            List<StoreProduct> storeProducts = this.storeProductRepository.findIdsInStore(productsIds, store.getId());
            for (StoreProduct sp: storeProducts) {
                int productQuantity = productQuantityMap.get(sp.getProductId());
                total += (sp.getPrice() * productQuantity);
            }

            StoreOrderBudget budget = new StoreOrderBudget();
            budget.setStoreId(store.getId());
            budget.setStoreName(store.getName());
            budget.setTotalPrice(total);
            budgets.add(budget);
        }

        return budgets;
    }
}
