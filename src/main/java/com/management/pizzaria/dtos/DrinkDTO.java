package com.management.pizzaria.dtos;

import java.math.BigDecimal;

public record DrinkDTO(
        String name,
        String drinkType,
        BigDecimal price
) {
}
