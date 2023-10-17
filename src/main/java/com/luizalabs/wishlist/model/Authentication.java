package com.luizalabs.wishlist.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document
public class Authentication {
    @Id
    private String id;

    private String token;
    private Date createdDate;
    private User user;

    public Authentication(User user) {
        this.user = user;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }
}
