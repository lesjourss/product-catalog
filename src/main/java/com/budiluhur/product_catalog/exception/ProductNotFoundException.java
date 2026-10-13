package com.budiluhur.product_catalog.exception;

/**
 * Exception yang dilempar ketika produk tidak ditemukan berdasarkan ID.
 */
public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
