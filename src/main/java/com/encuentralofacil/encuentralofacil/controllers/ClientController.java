package com.encuentralofacil.encuentralofacil.controllers;

import com.encuentralofacil.encuentralofacil.dto.ClientAuthDTO;
import com.encuentralofacil.encuentralofacil.entities.Client;
import com.encuentralofacil.encuentralofacil.repositories.ClientRepository;
import com.encuentralofacil.encuentralofacil.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/clients")
@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("")
    public ResponseEntity createClient(@RequestBody Client client) {
        try {
            this.clientService.createClient(client);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity loginClient(@RequestBody ClientAuthDTO clientAuth) {
        Client client;

        try {
            client = this.clientService.loginClient(clientAuth.getEmail(), clientAuth.getPassword());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(client);
    }
}
