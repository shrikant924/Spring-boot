package com.quiz_app.quiz_app.controller;

import com.quiz_app.quiz_app.model.Products;
import com.quiz_app.quiz_app.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/get/{id}")
    public Object getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct( @RequestPart MultipartFile imageFile , @RequestPart Products product ) throws IOException {
        return productService.addProduct(product,imageFile);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable long id){
       return productService.getProductImageById(id);
    }

    @PostMapping("/addToCart/{id}/{qty}")
    public Object addProductToCart(@PathVariable int id , @PathVariable  int qty){
        return productService.addProductToCart(id,qty);
    }

    @PutMapping(value = "/updateProduct/{id}" ,  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProductDetails(@PathVariable int id , @RequestPart Products product ,
                                                       @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {
        return productService.updateProductDetails(id , product ,imageFile);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return productService.deleteProductById(id);
    }
}