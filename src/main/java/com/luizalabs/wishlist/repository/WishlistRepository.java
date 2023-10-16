package com.luizalabs.wishlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luizalabs.wishlist.model.Wishlist;


public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    List<Wishlist> findByClientId(String clientId);

    Optional<Wishlist> findByClientIdAndProductId(String clientId, String productId);

    boolean existsByClientIdAndProductId(String clientId, String productId);

    void deleteByClientIdAndProductId(String clientId, String productId);
}
