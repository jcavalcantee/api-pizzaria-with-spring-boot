package com.management.pizzaria.services;

import com.management.pizzaria.dtos.ProductDTO;
import com.management.pizzaria.models.Product;
import com.management.pizzaria.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(Long id) throws Exception{
        return this.productRepository.findById(id).orElseThrow(
                () -> new Exception("Product with ID provided not found"));
    }

    public void deleteProduct(Long id) {
        try {
            this.productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
