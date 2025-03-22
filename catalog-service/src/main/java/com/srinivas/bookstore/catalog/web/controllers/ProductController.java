package com.srinivas.bookstore.catalog.web.controllers;

import com.srinivas.bookstore.catalog.domain.PageResult;
import com.srinivas.bookstore.catalog.domain.ProductEntity;
import com.srinivas.bookstore.catalog.domain.ProductNotFoundException;
import com.srinivas.bookstore.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PageResult<ProductEntity> getProducts(@RequestParam(name = "page", defaultValue = "1") int page) {
        return productService.getProducts(page);
    }

    @GetMapping("/{code}")
    ResponseEntity<ProductEntity> getProductByCode(@PathVariable("code") String code) {
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
