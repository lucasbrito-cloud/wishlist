package com.luizalabs.wishlist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.wishlist.dto.ProductDto;
import com.luizalabs.wishlist.exception.WishListException;
import com.luizalabs.wishlist.model.Client;
import com.luizalabs.wishlist.model.Product;
import com.luizalabs.wishlist.model.Wishlist;
import com.luizalabs.wishlist.repository.WishlistRepository;

@Service
public class WishlistService {

    private WishlistRepository wishlistRepository;
    private ClientService clientService;
    private ProductService productService;

    @Autowired
    public WishlistService(
            WishlistRepository wishlistRepository,
            ClientService clientService,
            ProductService productService) {
        this.wishlistRepository = wishlistRepository;
        this.clientService = clientService;
        this.productService = productService;
    }

    public List<ProductDto> getItems(String clientId) {
        List<Wishlist> wishlists = wishlistRepository.findByClientId(clientId);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Wishlist wishList : wishlists) {
            productDtos.add(productService.getProduct(wishList.getProduct()));
        }

        return productDtos;
    }

    public boolean isProduct(String clientId, String productId) {
        return wishlistRepository.existsByClientIdAndProductId(clientId, productId);
    }

    public Optional<Wishlist> getWishlistItem(String clientId, String productId) {
        return wishlistRepository.findByClientIdAndProductId(clientId, productId);
    }

    public ProductDto getProductDetails(Wishlist wishlistItem) {
        if (wishlistItem != null) {
            Product product = wishlistItem.getProduct();
            return productService.getProduct(product);
        } else {
            return null;
        }
    }

    public void addProduct(String clientId, Product product) {
        List<Wishlist> clientWishlist = wishlistRepository.findByClientId(clientId);
        if (clientWishlist.size() >= 20) {
            throw new WishListException("A Wishlist atingiu o limite máximo de 20 produtos.");
        }

        Optional<Client> clientOptional = clientService.getClientById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            Wishlist wishlistItem = new Wishlist(client, product);
            wishlistRepository.save(wishlistItem);
        } else {
            throw new WishListException("Cliente não encontrado");
        }
    }

    public void removeProduct(String clientId, String productId) {
        Optional<Wishlist> itemToRemove = wishlistRepository.findByClientIdAndProductId(clientId, productId);
        itemToRemove.ifPresent(wishlistRepository::delete);
    }

    public void clearWishlist(String clientId) {
        List<Wishlist> wishlists = wishlistRepository.findByClientId(clientId);
        wishlists.forEach(wishlistRepository::delete);
    }

}
