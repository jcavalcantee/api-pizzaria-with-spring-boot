package com.management.pizzaria.services;

import com.management.pizzaria.dtos.OrderDTO;
import com.management.pizzaria.dtos.ProductOrderDTO;
import com.management.pizzaria.exceptions.OrderNotFoundException;
import com.management.pizzaria.models.*;
import com.management.pizzaria.repositories.OrderRepository;
import com.management.pizzaria.repositories.ProductOrderRepository;
import com.management.pizzaria.repositories.ProductRepository;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public Order createOrder(List<ProductOrderDTO> itemsOrderDTO, PaymentType paymentType)throws Exception {
        Order order = new Order();
        order.setPaymentType(paymentType);
        order.setOrderDate(new Date());

        BigDecimal totalOrder = BigDecimal.ZERO;

        orderRepository.save(order);
        order.setOrderId(encontrarIdPorData(order.getOrderDate()));
        for (ProductOrderDTO productOrderDTO : itemsOrderDTO) {
            Long productId = productOrderDTO.productId();
            int quantity = productOrderDTO.quantity();

            Product product = this.productRepository.findById((long) productOrderDTO.productId())
                    .orElseThrow(() -> new Exception("Product not found!"));


            ProductOrder productOrder = new ProductOrder();
            productOrder.setId(new ProductOrderKey(order, product));
            productOrder.setQuantity(productOrderDTO.quantity());

            productOrderRepository.save(productOrder);

            BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            totalOrder = totalOrder.add(subTotal);
        }

        return order;
    }

    private Long encontrarIdPorData(Date dataPedido){
        return orderRepository.encontrarIdPorData(dataPedido);
    }

    public List<Order> listAllOrders() {
        return this.orderRepository.findAll();
    }

    public Order getOrderById(Long id) throws OrderNotFoundException {
        return this.orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id provided not found"));
    }

    public ResponseEntity<Order> deleteOrderById(Long id) {

        if (orderRepository.findById(id).isPresent()) {
            this.orderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
