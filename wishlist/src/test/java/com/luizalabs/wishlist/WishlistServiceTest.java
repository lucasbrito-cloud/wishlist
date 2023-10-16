package com.luizalabs.wishlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.luizalabs.wishlist.dto.ProductDto;
import com.luizalabs.wishlist.model.Client;
import com.luizalabs.wishlist.model.Product;
import com.luizalabs.wishlist.model.Wishlist;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.service.ClientService;
import com.luizalabs.wishlist.service.ProductService;
import com.luizalabs.wishlist.service.WishlistService;

public class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private ProductService productService;

    private WishlistService wishlistService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        wishlistService = new WishlistService(wishlistRepository, clientService, productService);
    }

    @Test
    public void testGetItems() {
        String clientId = "1";

        List<ProductDto> result = wishlistService.getItems(clientId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIsProduct() {
        String clientId = "1";
        String productId = "1";

        when(wishlistRepository.existsByClientIdAndProductId(clientId, productId)).thenReturn(true);

        boolean result = wishlistService.isProduct(clientId, productId);

        assertTrue(result);
    }

    @Test
    public void testGetWishlistItem() {
        String clientId = "1";
        String productId = "1";

        Wishlist wishlistItem = new Wishlist(new Client(clientId, "Lucas Brito"), new Product("Mouse Logitech", 29.99));

        when(wishlistRepository.findByClientIdAndProductId(clientId, productId)).thenReturn(Optional.of(wishlistItem));

        Optional<Wishlist> result = wishlistService.getWishlistItem(clientId, productId);

        assertTrue(result.isPresent());
        assertEquals(wishlistItem, result.get());
    }

    @Test
    public void testAddProduct() {
        String clientId = "1";
        Product product = new Product("Teclado Mancer", 203.34);
        Client client = new Client(clientId, "Lucas");

        List<Wishlist> emptyWishlist = new ArrayList<>();
        when(wishlistRepository.findByClientId(clientId)).thenReturn(emptyWishlist);
        when(clientService.getClientById(clientId)).thenReturn(Optional.of(client));

        wishlistService.addProduct(clientId, product);

        Mockito.verify(wishlistRepository).save(Mockito.any(Wishlist.class));
    }

    @Test
    public void testRemoveProduct() {
        String clientId = "1";
        String productId = "1";

        Wishlist wishlistItem = new Wishlist(new Client(clientId, "Lucas Brito"),
                new Product("Teclado Mancer", 203.34));
        when(wishlistRepository.findByClientIdAndProductId(clientId, productId)).thenReturn(Optional.of(wishlistItem));

        wishlistService.removeProduct(clientId, productId);

        verify(wishlistRepository).delete(wishlistItem);
    }

    @Test
    public void testClearWishlist() {
        String clientId = "1";
        List<Wishlist> wishlistItems = new ArrayList<>();
        wishlistItems.add(new Wishlist(new Client(clientId, "Lucas Brito"), new Product("Mouse Logitech", 29.99)));
        wishlistItems.add(new Wishlist(new Client(clientId, "Lucas Brito"), new Product("Teclado Mancer", 203.34)));

        when(wishlistRepository.findByClientId(clientId)).thenReturn(wishlistItems);

        wishlistService.clearWishlist(clientId);

        verify(wishlistRepository, times(wishlistItems.size())).delete(any(Wishlist.class));
    }

}
