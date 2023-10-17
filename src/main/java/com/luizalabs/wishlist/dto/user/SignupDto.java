package com.luizalabs.wishlist.dto.user;

import lombok.Data;

@Data
public class SignupDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public SignupDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

}
