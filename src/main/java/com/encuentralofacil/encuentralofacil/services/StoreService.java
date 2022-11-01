package com.encuentralofacil.encuentralofacil.services;

import com.encuentralofacil.encuentralofacil.entities.Store;
import com.encuentralofacil.encuentralofacil.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public void createStore(Store store) {
        this.storeRepository.save(store);
    }

    public Store loginStore(String email, String password) throws Exception {
        Store store;

        Optional<Store> storeFound = this.storeRepository.findByEmail(email);
        if (storeFound.isEmpty()) throw new Exception("invalid credentials");

        store = storeFound.get();
        if (!store.getPassword().equals(password)) throw new Exception("invalid credentials");

        store.setPassword("");
        return store;
    }
}
