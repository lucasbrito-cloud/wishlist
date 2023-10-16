package com.luizalabs.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luizalabs.wishlist.common.ApiResponse;
import com.luizalabs.wishlist.common.ProductCheckResult;
import com.luizalabs.wishlist.dto.ProductDto;
import com.luizalabs.wishlist.model.Product;
import com.luizalabs.wishlist.model.Wishlist;
import com.luizalabs.wishlist.service.WishlistService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    /* Buscar todos os itens da Wishlist */
    @GetMapping("/{clientId}")
    public ResponseEntity<List<ProductDto>> getItems(@PathVariable String clientId) {
        List<ProductDto> productDtos = wishlistService.getItems(clientId);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    /* Verificar se o item específico existe na Wishlist */
    @GetMapping("/contains/{clientId}/{productId}")
    public ResponseEntity<ProductCheckResult> isProduct(@PathVariable String clientId, @PathVariable String productId) {
        boolean result = wishlistService.isProduct(clientId, productId);
        ProductCheckResult productCheckResult = new ProductCheckResult(result);
        return new ResponseEntity<>(productCheckResult, HttpStatus.OK);
    }

    /* Buscar um item específico da Wishlist */
    @GetMapping("/product/{clientId}/{productId}")
    public ResponseEntity<ProductDto> getProductDetails(@PathVariable String clientId, @PathVariable String productId) {
        Optional<Wishlist> wishlistItem = wishlistService.getWishlistItem(clientId, productId);

        if (wishlistItem.isPresent()) {
            ProductDto productDto = wishlistService.getProductDetails(wishlistItem.get());
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* Adicionar item na Wishlist */
    @PostMapping("/add/{clientId}")
    public ResponseEntity<ApiResponse> addProduct(@PathVariable String clientId, @RequestBody Product product) {
        wishlistService.addProduct(clientId, product);
        ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    /* Remover item na Wishlist */
    @DeleteMapping("/remove/{clientId}/{productId}")
    public ResponseEntity<ApiResponse> removeProduct(@PathVariable String clientId, @PathVariable String productId) {
        wishlistService.removeProduct(clientId, productId);
        ApiResponse apiResponse = new ApiResponse(true, "Removed from wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /* Limpar Wishlist */
    @DeleteMapping("/clear/{clientId}")
    public ResponseEntity<ApiResponse> clearWishlist(@PathVariable String clientId) {
        wishlistService.clearWishlist(clientId);
        ApiResponse apiResponse = new ApiResponse(true, "Wishlist cleared");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
