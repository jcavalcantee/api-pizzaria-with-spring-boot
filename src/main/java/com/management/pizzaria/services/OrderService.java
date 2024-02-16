package com.management.pizzaria.services;

import com.management.pizzaria.dtos.ProductOrderDTO;
import com.management.pizzaria.models.Order;
import com.management.pizzaria.models.PaymentType;
import com.management.pizzaria.models.Product;
import com.management.pizzaria.models.ProductOrder;
import com.management.pizzaria.repositories.OrderRepository;
import com.management.pizzaria.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(List<ProductOrderDTO> itemsOrderDTO, PaymentType paymentType)throws Exception {
        Order order = new Order();
        order.setPaymentType(paymentType);
        order.setOrderDate(new Date());

        BigDecimal totalOrder = BigDecimal.ZERO;

        for (ProductOrderDTO productOrderDTO : itemsOrderDTO) {
            Product product = this.productRepository.findById((long) productOrderDTO.productId())
                    .orElseThrow(() -> new Exception("Product not found!"));

            ProductOrder productOrder = new ProductOrder();
            productOrder.setProduct(product);
            productOrder.setQuantity(productOrderDTO.quantity());

            BigDecimal subtotal = totalOrder.add(product.getPrice().multiply(BigDecimal.valueOf(productOrderDTO.quantity())));
            totalOrder = totalOrder.add(subtotal);
        }

        orderRepository.save(order);
        return order;
    }
}
