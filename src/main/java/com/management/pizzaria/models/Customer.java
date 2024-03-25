package com.management.pizzaria.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.management.pizzaria.dtos.CustomerDTO;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Entity
@Table(name = "TB_CUSTOMERS")
public class Customer extends RepresentationModel<Customer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, name = "Nome")
    private String name;
    @Column(unique = true, name = "Telefone")
    private String telphone;

    public Customer() {
    }

    public Customer(Long id, String name, String telphone) {
        this.id = id;
        this.name = name;
        this.telphone = telphone;
    }

    public Customer(CustomerDTO customerDTO) {
        this.name = customerDTO.name();
        this.telphone = customerDTO.telphone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(telphone, customer.telphone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, telphone);
    }
}
