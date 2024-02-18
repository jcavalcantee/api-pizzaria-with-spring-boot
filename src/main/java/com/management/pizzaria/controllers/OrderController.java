package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.OrderDTO;
import com.management.pizzaria.dtos.ProductOrderDTO;
import com.management.pizzaria.exceptions.OrderNotFoundException;
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

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        var allOrders = orderService.listAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable (value = "id") Long id) throws OrderNotFoundException {
        var order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Order> deleteOrderById(@PathVariable (value = "id") Long id) {
        return orderService.deleteOrderById(id);
    }
}
