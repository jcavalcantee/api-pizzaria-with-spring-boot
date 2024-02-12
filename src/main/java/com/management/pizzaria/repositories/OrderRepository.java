package com.management.pizzaria.repositories;

import com.management.pizzaria.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
