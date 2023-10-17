package com.luizalabs.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luizalabs.wishlist.dto.ProductCheckResultDto;
import com.luizalabs.wishlist.dto.ProductDto;
import com.luizalabs.wishlist.dto.WishlistResponseDto;
import com.luizalabs.wishlist.model.Product;
import com.luizalabs.wishlist.model.User;
import com.luizalabs.wishlist.model.Wishlist;
import com.luizalabs.wishlist.service.AuthenticationService;
import com.luizalabs.wishlist.service.WishlistService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    AuthenticationService authenticationService;

    /* Buscar todos os itens da Wishlist */
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getItems(@PathVariable("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        List<ProductDto> productDtos = wishlistService.getItems(user);
        
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    /* Verificar se o item específico existe na Wishlist */
    @GetMapping("/contains/{token}/{productId}")
    public ResponseEntity<ProductCheckResultDto> isProduct(@PathVariable("token") String token,
            @PathVariable("productId") String productId) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        boolean result = wishlistService.isProduct(user, productId);

        ProductCheckResultDto productCheckResult = new ProductCheckResultDto(result);
        return new ResponseEntity<>(productCheckResult, HttpStatus.OK);
    }

    /* Buscar um item específico da Wishlist */
    @GetMapping("/product/{token}/{productId}")
    public ResponseEntity<ProductDto> getProductDetails(@PathVariable("token") String token,
            @PathVariable("productId") String productId) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        Optional<Wishlist> wishlistItem = wishlistService.getWishlistItem(user, productId);

        if (wishlistItem.isPresent()) {
            ProductDto productDto = wishlistService.getProductDetails(wishlistItem.get());
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* Adicionar item na Wishlist */
    @PostMapping("/add")
    public ResponseEntity<WishlistResponseDto> addProduct(@RequestParam("token") String token,
            @RequestBody Product product) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        Wishlist wishList = new Wishlist(product, user);
        wishlistService.addProduct(wishList);

        WishlistResponseDto wishlistResponse = new WishlistResponseDto(true, "Added to wishlist");
        return new ResponseEntity<>(wishlistResponse, HttpStatus.CREATED);
    }

    /* Remover item na Wishlist */
    @DeleteMapping("/remove/{token}/{productId}")
    public ResponseEntity<WishlistResponseDto> removeProduct(@RequestParam("token") String token,
            @PathVariable("productId") String productId) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        wishlistService.removeProduct(user, productId);

        WishlistResponseDto wishlistResponse = new WishlistResponseDto(true, "Removed from wishlist");
        return new ResponseEntity<>(wishlistResponse, HttpStatus.OK);
    }

    /* Limpar Wishlist */
    @DeleteMapping("/clear/{token}")
    public ResponseEntity<WishlistResponseDto> clearWishlist(@RequestParam("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);

        wishlistService.clearWishlist(user);

        WishlistResponseDto wishlistResponse = new WishlistResponseDto(true, "Wishlist cleared");
        return new ResponseEntity<>(wishlistResponse, HttpStatus.OK);
    }

}
