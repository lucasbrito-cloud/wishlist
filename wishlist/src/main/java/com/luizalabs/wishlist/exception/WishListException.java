package com.luizalabs.wishlist.exception;

public class WishListException extends RuntimeException {
    public WishListException(String message) {
        super(message);
    }

    public WishListException(String message, Throwable cause) {
        super(message, cause);
    }
}
