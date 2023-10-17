package com.luizalabs.wishlist.dto.user;

import lombok.Data;

@Data
public class SignInReponseDto {

    private String token;

    public SignInReponseDto(String token) {
        this.token = token;
    }

}
