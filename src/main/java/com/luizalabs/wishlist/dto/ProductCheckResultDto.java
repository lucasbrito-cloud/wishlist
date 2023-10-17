package com.luizalabs.wishlist.dto;

import lombok.Data;

@Data
public class ProductCheckResultDto {
    private boolean isProduct;

    public ProductCheckResultDto(boolean isProduct) {
        this.isProduct = isProduct;
    }
}
