package com.management.pizzaria.dtos;

import com.management.pizzaria.models.PaymentType;

import java.util.Date;
import java.util.List;

public record OrderDTO(
    Long paymentId,
    Date orderDate,
    PaymentType paymentType,
    List<ProductOrderDTO> itemsOrder
) {
}