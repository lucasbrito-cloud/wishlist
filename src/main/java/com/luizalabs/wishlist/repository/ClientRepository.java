package com.luizalabs.wishlist.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luizalabs.wishlist.model.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByName(String name);
}
