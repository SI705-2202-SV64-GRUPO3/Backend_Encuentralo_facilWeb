package com.encuentralofacil.encuentralofacil.services;

import com.encuentralofacil.encuentralofacil.entities.Client;
import com.encuentralofacil.encuentralofacil.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void createClient(Client client) {
        this.clientRepository.save(client);
    }

    public Client loginClient(String email, String password) throws Exception {
        Optional<Client> clientFound = this.clientRepository.findByEmail(email);
        if (clientFound.isEmpty()) throw new Exception("invalid credentials");

        Client client = clientFound.get();
        if (!client.getPassword().equals(password)) throw new Exception("invalid credentials");

        client.setPassword("");
        return client;
    }
}
