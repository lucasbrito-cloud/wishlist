package com.luizalabs.wishlist.common;

public class ProductCheckResult {
    private boolean isProduct;

    public ProductCheckResult(boolean isProduct) {
        this.isProduct = isProduct;
    }

    public boolean isProduct() {
        return isProduct;
    }

    public void setProduct(boolean product) {
        isProduct = product;
    }
}
