package com.management.pizzaria.dtos;

import com.management.pizzaria.models.Customer;

public class AddressRequestDTO {

    private String cep;
    private String numero;
    private Long id;

    public AddressRequestDTO() {

    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
