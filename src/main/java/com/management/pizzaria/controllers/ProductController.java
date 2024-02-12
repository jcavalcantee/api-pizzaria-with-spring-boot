package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.ProductDTO;
import com.management.pizzaria.models.Product;
import com.management.pizzaria.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = this.productService.createProduct(productDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        var allProducts = this.productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable (value = "id") Long id) throws Exception {
        var product = this.productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable (value = "id") Long id,
                                                 @RequestBody Product product) throws Exception {
        var productUpdate = this.productService.updateProduct(id, product);
        return new ResponseEntity<>(productUpdate, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable (value = "id") Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
