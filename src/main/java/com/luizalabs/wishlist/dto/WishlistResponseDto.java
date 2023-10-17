package com.luizalabs.wishlist.dto;

import lombok.Data;

@Data
public class WishlistResponseDto {

    private final boolean success;
    private final String message;

    public WishlistResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
