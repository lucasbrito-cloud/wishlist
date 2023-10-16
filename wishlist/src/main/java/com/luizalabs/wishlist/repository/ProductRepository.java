package com.luizalabs.wishlist.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luizalabs.wishlist.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String name);
}
