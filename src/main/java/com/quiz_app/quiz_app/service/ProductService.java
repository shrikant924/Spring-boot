package com.quiz_app.quiz_app.service;

import com.quiz_app.quiz_app.model.Cart;
import com.quiz_app.quiz_app.model.Products;
import com.quiz_app.quiz_app.repo.CartRepo;
import com.quiz_app.quiz_app.repo.ProductRepo;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Page<Products> loadAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    public ResponseEntity<String> addProduct(Products product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        Products savedProduct = productRepo.save(product);

        if (savedProduct.getId() != null) {
            return ResponseEntity.ok("Product added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add product");
        }
    }

    public ResponseEntity<byte[]> getProductImageById(long id) {
        Products product = productRepo.getProductsById(id).get(0);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(product.getImageData());
    }

    public Object addProductToCart(long userId, long productId, int qty) {
        Products product = productRepo.getProductsById(productId).get(0);
        int productBalanceStock = product.getStock();
        Cart cart = null;
        if (productBalanceStock >= qty) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setProductQty(qty);
            cartRepo.save(cart);

            product.setStock(productBalanceStock-qty);
            productRepo.save(product);
            return new ResponseEntity<>(product.getStock(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product is out of stock", HttpStatus.OK);
        }
    }

    public ResponseEntity<String> updateProductDetails(long id , Products products ,  MultipartFile imageFile) throws IOException {
        Optional<Products> product = productRepo.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Products existingProduct = product.get();

        if (imageFile != null && !imageFile.isEmpty()) {
            existingProduct.setImageName(imageFile.getOriginalFilename());
            existingProduct.setImageType(imageFile.getContentType());
            existingProduct.setImageData(imageFile.getBytes());
            productRepo.save(existingProduct);
        }

        existingProduct.setName(products.getName());
        existingProduct.setCategory(products.getCategory());
        existingProduct.setDescription(products.getDescription());
        existingProduct.setDiscount(products.getDiscount());
        existingProduct.setBrand(products.getBrand());
        existingProduct.setPrice(products.getPrice());
        existingProduct.setOriginalPrice(products.getOriginalPrice());
        existingProduct.setReviews(products.getReviews());
        existingProduct.setStock(products.getStock());
        existingProduct.setRating(products.getRating());
        productRepo.save(existingProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body("Product Updated Successfully");
    }

    public Object getProductById(long id) {
        Optional<Products> product = productRepo.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not found");
        }

        return new ResponseEntity<>(product , HttpStatus.OK) ;   }

    public ResponseEntity<String> deleteProductById(long id) {
        productRepo.deleteById(id);
        return ResponseEntity.ok("Product Delected from DB");
    }
}