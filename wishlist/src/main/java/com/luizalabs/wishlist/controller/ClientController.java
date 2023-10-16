package com.luizalabs.wishlist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.wishlist.model.Client;
import com.luizalabs.wishlist.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /* Buscar todos os clientes */
    @GetMapping("/")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> products = clientService.getClients();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /* Buscar cliente por id */
    @GetMapping("/id/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        Optional<Client> client = clientService.getClientById(id);

        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* Buscar cliente por nome */
    @GetMapping("/name/{name}")
    public ResponseEntity<Client> getClientByName(@PathVariable String name) {
        Client client = clientService.getClientByName(name);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
