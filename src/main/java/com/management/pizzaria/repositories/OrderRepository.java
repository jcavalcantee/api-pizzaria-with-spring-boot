package com.management.pizzaria.repositories;

import com.management.pizzaria.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o.id FROM Order o WHERE o.orderDate = :dataPedido")
    Long findIdByDate(@Param("dataPedido") Date dataPedido);
}
