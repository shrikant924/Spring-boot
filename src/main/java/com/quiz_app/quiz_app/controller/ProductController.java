package com.quiz_app.quiz_app.controller;

import com.quiz_app.quiz_app.model.Products;
import com.quiz_app.quiz_app.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Products>> getProducts(){
        return productService.loadAllProducts();
    }
}
