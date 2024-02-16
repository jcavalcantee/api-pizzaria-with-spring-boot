package com.management.pizzaria.dtos;

import com.management.pizzaria.models.Order;
import com.management.pizzaria.models.Product;

import java.util.List;

public record ProductOrderDTO(
        int orderId,
        int productId,
        int quantity,
        List<ProductOrderDTO> itemsOrder
) {
}
