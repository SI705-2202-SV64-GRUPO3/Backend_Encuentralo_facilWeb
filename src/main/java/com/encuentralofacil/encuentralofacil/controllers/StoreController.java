package com.encuentralofacil.encuentralofacil.controllers;

import com.encuentralofacil.encuentralofacil.dto.StoreAuthDTO;
import com.encuentralofacil.encuentralofacil.entities.Client;
import com.encuentralofacil.encuentralofacil.entities.Store;
import com.encuentralofacil.encuentralofacil.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/stores")
@Controller
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping("")
    public ResponseEntity createStore(@RequestBody Store store) {
        try {
            this.storeService.createStore(store);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity loginStore(@RequestBody StoreAuthDTO storeAuth) {
        Store store;

        try {
            store = this.storeService.loginStore(storeAuth.getEmail(), storeAuth.getPassword());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(store);
    }
}
