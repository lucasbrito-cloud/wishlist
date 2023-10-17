package com.luizalabs.wishlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luizalabs.wishlist.model.User;
import com.luizalabs.wishlist.model.Wishlist;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    List<Wishlist> findByUser(User user);

    Optional<Wishlist> findByUserAndProductId(User user, String productId);

    boolean existsByUserAndProductId(User user, String productId);

    void deleteByUserIdAndProductId(User user, String productId);
}
