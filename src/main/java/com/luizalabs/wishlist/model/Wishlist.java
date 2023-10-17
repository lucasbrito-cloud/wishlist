package com.luizalabs.wishlist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Wishlist {

    @Id
    private String id;

    private Product product;
    private User user;

    public Wishlist(Product product, User user) {
        this.product = product;
        this.user = user;
    }

}
