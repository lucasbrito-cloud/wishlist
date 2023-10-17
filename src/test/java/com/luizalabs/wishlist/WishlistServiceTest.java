package com.luizalabs.wishlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.luizalabs.wishlist.dto.ProductDto;
import com.luizalabs.wishlist.model.Product;
import com.luizalabs.wishlist.model.User;
import com.luizalabs.wishlist.model.Wishlist;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.service.ProductService;
import com.luizalabs.wishlist.service.WishlistService;

public class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private ProductService productService;

    private WishlistService wishlistService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        wishlistService = new WishlistService(wishlistRepository, productService);
    }

    @Test
    public void testGetItems() {
        User user = new User("Lucas", "Brito", "lucasbrito@email.com", "password");
        List<Wishlist> wishlists = new ArrayList<>();
        when(wishlistRepository.findByUser(user)).thenReturn(wishlists);

        List<ProductDto> result = wishlistService.getItems(user);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIsProduct() {
        User user = new User("Lucas", "Brito", "lucasbrito@email.com", "password");
        String productId = "1";

        when(wishlistRepository.existsByUserAndProductId(user, productId)).thenReturn(true);

        boolean result = wishlistService.isProduct(user, productId);

        assertTrue(result);
    }

    @Test
    public void testGetWishlistItem() {
        User user = new User("Lucas", "Brito", "lucasbrito@email.com", "password");
        String productId = "1";

        Wishlist wishlistItem = new Wishlist(new Product("Mouse Logitech", 29.99), user);
        when(wishlistRepository.findByUserAndProductId(user, productId)).thenReturn(Optional.of(wishlistItem));

        Optional<Wishlist> result = wishlistService.getWishlistItem(user, productId);

        assertTrue(result.isPresent());
        assertEquals(wishlistItem, result.get());
    }

    @Test
    public void testAddProduct() {
        User user = new User("Lucas", "Brito", "lucasbrito@email.com", "password");
        Product product = new Product("Teclado Mancer", 203.34);

        List<Wishlist> emptyWishlist = new ArrayList<>();
        when(wishlistRepository.findByUser(user)).thenReturn(emptyWishlist);

        wishlistService.addProduct(new Wishlist(product, user));

        verify(wishlistRepository).save(Mockito.any(Wishlist.class));
    }

    @Test
    public void testRemoveProduct() {
        User user = new User("Lucas", "Brito", "lucasbrito@email.com", "password");
        String productId = "1";

        Wishlist wishlistItem = new Wishlist(new Product("Teclado Mancer", 203.34), user);
        when(wishlistRepository.findByUserAndProductId(user, productId)).thenReturn(Optional.of(wishlistItem));

        wishlistService.removeProduct(user, productId);

        verify(wishlistRepository).delete(wishlistItem);
    }

    @Test
    public void testClearWishlist() {
        User user = new User("Lucas", "Brito", "lucasbrito@email.com", "password");
        List<Wishlist> wishlistItems = new ArrayList<>();
        wishlistItems.add(new Wishlist(new Product("Mouse Logitech", 29.99), user));
        wishlistItems.add(new Wishlist(new Product("Teclado Mancer", 203.34), user));

        when(wishlistRepository.findByUser(user)).thenReturn(wishlistItems);

        wishlistService.clearWishlist(user);

        verify(wishlistRepository, times(wishlistItems.size())).delete(Mockito.any(Wishlist.class));
    }
}
