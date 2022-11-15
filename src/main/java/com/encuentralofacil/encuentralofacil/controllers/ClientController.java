package com.encuentralofacil.encuentralofacil.controllers;

import com.encuentralofacil.encuentralofacil.dto.ClientAuthDTO;
import com.encuentralofacil.encuentralofacil.dto.UpdateClientDTO;
import com.encuentralofacil.encuentralofacil.entities.Client;
import com.encuentralofacil.encuentralofacil.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/clients")
@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("{id}")
    public ResponseEntity getClientById(@PathVariable("id") String id) {
        Client client;

        try {
            Long clientId = Long.parseLong(id);
            client = this.clientService.getClientById(clientId);
            client.setPassword("");
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(client);
    }

    @PostMapping("")
    public ResponseEntity createClient(@RequestBody Client client) {
        try {
            this.clientService.createClient(client);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity editClient(@PathVariable("id") String id, @RequestBody UpdateClientDTO client) {
        try {
            Long clientId = Long.parseLong(id);

            Client existingClient = this.clientService.getClientById(clientId);
            existingClient.setFirstName(client.getFirstName());
            existingClient.setLastName(client.getLastName());
            existingClient.setEmail(client.getEmail());

            this.clientService.editClient(clientId, existingClient);
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
