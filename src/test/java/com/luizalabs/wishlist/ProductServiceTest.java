package com.luizalabs.wishlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.luizalabs.wishlist.dto.ProductDto;
import com.luizalabs.wishlist.model.Product;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testGetProductById() {
        String productId = "1";
        Product product = new Product("Teclado Mancer", 203.34);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(productId);

        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Notebook Dell", 2000.0));
        products.add(new Product("Mouse", 115.0));

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(products.size(), result.size());
        assertEquals(products, result);
    }

    @Test
    public void testGetProductByName() {
        String productName = "Teclado Mancer";
        Product product = new Product(productName, 203.34);

        when(productRepository.findByName(productName)).thenReturn(product);

        Product result = productService.getProductByName(productName);

        assertNotNull(result);
        assertEquals(product, result);
    }

    @Test
    public void testGetProduct() {
        Product product = new Product("Iphone 13", 3419.99);

        ProductDto result = productService.getProduct(product);

        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
    }
}
