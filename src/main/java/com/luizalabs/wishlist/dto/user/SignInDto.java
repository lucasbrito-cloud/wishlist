package com.luizalabs.wishlist.dto.user;

import lombok.Data;

@Data
public class SignInDto {

    private String email;
    private String password;

    public SignInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
