package com.luizalabs.wishlist.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luizalabs.wishlist.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
