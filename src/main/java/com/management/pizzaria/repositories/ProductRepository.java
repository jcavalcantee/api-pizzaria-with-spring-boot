package com.management.pizzaria.repositories;

import com.management.pizzaria.models.Customer;
import com.management.pizzaria.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
