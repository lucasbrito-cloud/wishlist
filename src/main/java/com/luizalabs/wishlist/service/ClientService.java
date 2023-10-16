package com.luizalabs.wishlist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.wishlist.model.Client;
import com.luizalabs.wishlist.repository.ClientRepository;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(
            ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> getClientById(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("O ID do cliente não pode estar vazio.");
        }
        return clientRepository.findById(clientId);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientByName(String name) {
        return clientRepository.findByName(name);
    }

}
