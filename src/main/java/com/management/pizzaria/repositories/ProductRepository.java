package com.management.pizzaria.repositories;

import com.management.pizzaria.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Customer, Long> {
}
