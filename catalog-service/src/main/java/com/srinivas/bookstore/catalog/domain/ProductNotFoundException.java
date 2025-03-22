package com.srinivas.bookstore.catalog.domain;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String s) {
        super(s);
    }

    public static ProductNotFoundException forCode(String code) {
        return new ProductNotFoundException("product not found with code: " + code);
    }
}
