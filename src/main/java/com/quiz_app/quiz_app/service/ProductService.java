package com.quiz_app.quiz_app.service;

import com.quiz_app.quiz_app.model.Products;
import com.quiz_app.quiz_app.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ResponseEntity<List<Products>> loadAllProducts() {
        List<Products> products = productRepo.findAll();
        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> addProduct(Products product) {
        Products savedProduct = productRepo.save(product);

        if (savedProduct.getId() != null) {
            return ResponseEntity.ok("Product added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add product");
        }
    }
}
