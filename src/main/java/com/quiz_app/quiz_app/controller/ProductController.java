package com.quiz_app.quiz_app.controller;

import com.quiz_app.quiz_app.model.Cart;
import com.quiz_app.quiz_app.model.Products;
import com.quiz_app.quiz_app.model.dto.PaginationResponse;
import com.quiz_app.quiz_app.repo.ProductRepo;
import com.quiz_app.quiz_app.service.ProductService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("product")
public class ProductController {

  private final ProductService productService;
  private final ProductRepo productRepo;

  public ProductController(ProductService productService, ProductRepo productRepo) {
    this.productService = productService;
      this.productRepo = productRepo;
  }

  @GetMapping("/getProducts")
  public Page<Products> getProducts(@RequestParam int page , @RequestParam int size) {
      Pageable pageable = PageRequest.of(
              page,
              size,
              Sort.by("id").descending()
      );
    return productService.loadAllProducts(pageable);
  }

  @GetMapping("/get/{id}")
  public Object getProductById(@PathVariable int id) {
    return productService.getProductById(id);
  }

  @PostMapping("/addProduct")
  public ResponseEntity<String> addProduct(
      @RequestPart MultipartFile imageFile, @RequestPart Products product) throws IOException {
    return productService.addProduct(product, imageFile);
  }

  @GetMapping("/image/{id}")
  public ResponseEntity<byte[]> getImageById(@PathVariable long id) {
    return productService.getProductImageById(id);
  }

  @PostMapping("/addToCart")
  public Object addProductToCart(@RequestBody Cart cart) {
    return productService.addProductToCart(cart.getUserId(), cart.getProductId(), cart.getProductQty());
  }

  @PutMapping(value = "/updateProduct/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> updateProductDetails(
      @PathVariable int id,
      @RequestPart Products product,
      @RequestPart(value = "imageFile", required = false) MultipartFile imageFile)
      throws IOException {
    return productService.updateProductDetails(id, product, imageFile);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable long id) {
    return productService.deleteProductById(id);
  }

}