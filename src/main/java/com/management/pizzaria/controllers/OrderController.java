package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.OrderDTO;
import com.management.pizzaria.dtos.ProductOrderDTO;
import com.management.pizzaria.models.Order;
import com.management.pizzaria.models.PaymentType;
import com.management.pizzaria.repositories.OrderRepository;
import com.management.pizzaria.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        Order newOrder = orderService.createOrder(orderDTO.itemsOrder(), orderDTO.paymentType());
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
}
