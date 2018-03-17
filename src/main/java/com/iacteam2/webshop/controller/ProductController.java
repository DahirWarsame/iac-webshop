package com.iacteam2.webshop.controller;

import com.iacteam2.webshop.exception.ResourceNotFoundException;
import com.iacteam2.webshop.model.Product;
import com.iacteam2.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/rest/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    // Get All Products
    @GetMapping("")
    public List<Product> getAllNotes() {
        return productRepository.findAll();
    }
    // Create a new Product
    @PostMapping("/new")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }
    // Get a Single Product
    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }
    // Update a Product
    @PutMapping("/delete/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long productId,
                           @Valid @RequestBody Product productDetails) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());

        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }
    // Delete a Product
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }
}
