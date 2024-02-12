package com.management.pizzaria.dtos;

import java.math.BigDecimal;

public record PizzaDTO(
        String flavor,
        String ingredients,
        BigDecimal price
) {
}
