package com.iacteam2.webshop.controller;

import com.iacteam2.webshop.exception.ResourceNotFoundException;
import com.iacteam2.webshop.model.Category;
import com.iacteam2.webshop.model.Product;
import com.iacteam2.webshop.repository.CategoryRepository;
import com.iacteam2.webshop.repository.ProductRepository;
import org.hibernate.*;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rest/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    // Get All Products
    @GetMapping("")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // Create a new Product
    @PostMapping("/new")
    public Product createProduct(@Valid @RequestBody Product product) {
        CategoryController c = null;
        System.out.println(c.getCategoryById(Long.valueOf(4)).toString());
        return productRepository.save(product);
    }

    // Get a Single Product
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    // Update a Product
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }
}
