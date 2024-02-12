package com.management.pizzaria.dtos;

public record CustomerDTO(
        String name,
        String telphone,
        String zipCode,
        String street,
        String district,
        String number
    ) {
}
