package com.luizalabs.wishlist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.wishlist.dto.ProductDto;
import com.luizalabs.wishlist.exception.WishListException;
import com.luizalabs.wishlist.model.Product;
import com.luizalabs.wishlist.model.User;
import com.luizalabs.wishlist.model.Wishlist;
import com.luizalabs.wishlist.repository.WishlistRepository;

@Service
public class WishlistService {

    private WishlistRepository wishlistRepository;
    private ProductService productService;

    @Autowired
    public WishlistService(
            WishlistRepository wishlistRepository,
            ProductService productService) {
        this.wishlistRepository = wishlistRepository;
        this.productService = productService;
    }

    public List<ProductDto> getItems(User user) {

        List<Wishlist> wishlists = wishlistRepository.findByUser(user);
        return wishlists.stream()
                .map(wishlist -> productService.getProduct(wishlist.getProduct()))
                .collect(Collectors.toList());
    }

    public boolean isProduct(User user, String productId) {
        return wishlistRepository.existsByUserAndProductId(user, productId);
    }

    public Optional<Wishlist> getWishlistItem(User user, String productId) {
        return wishlistRepository.findByUserAndProductId(user, productId);
    }

    public ProductDto getProductDetails(Wishlist wishlist) {
        if (wishlist != null) {
            Product product = wishlist.getProduct();
            return productService.getProduct(product);
        } else {
            return null;
        }
    }

    public void addProduct(Wishlist wishlist) {
        User user = wishlist.getUser();
        List<Wishlist> wishlists = wishlistRepository.findByUser(user);
        if (wishlists.size() >= 20) {
            throw new WishListException("The Wishlist has reached the maximum limit of 20 products.");
        }

        wishlistRepository.save(wishlist);
    }

    public void removeProduct(User user, String productId) {
        Optional<Wishlist> itemToRemove = wishlistRepository.findByUserAndProductId(user, productId);
        itemToRemove.ifPresent(wishlistRepository::delete);
    }

    public void clearWishlist(User user) {
        List<Wishlist> wishlists = wishlistRepository.findByUser(user);
        wishlists.forEach(wishlistRepository::delete);
    }

}
