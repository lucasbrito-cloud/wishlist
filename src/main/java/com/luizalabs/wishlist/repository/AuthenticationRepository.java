package com.luizalabs.wishlist.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luizalabs.wishlist.model.Authentication;
import com.luizalabs.wishlist.model.User;

public interface AuthenticationRepository extends MongoRepository<Authentication, String> {
    Authentication findByUser(User user);

    Authentication findByToken(String token);
}
