package com.management.pizzaria.repositories;

import com.management.pizzaria.models.ProductOrder;
import com.management.pizzaria.models.ProductOrderKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderKey> {
}
