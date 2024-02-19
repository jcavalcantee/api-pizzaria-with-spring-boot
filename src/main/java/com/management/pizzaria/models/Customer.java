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
    private Long key;
    @Column(nullable = false, name = "Nome")
    private String name;
    @Column(unique = true, name = "Telefone")
    private String telphone;
    @Column(nullable = false, name = "CEP")
    private String zipCode;
    @Column(nullable = false, name = "Rua")
    private String street;
    @Column(nullable = false, name = "Numero_casa")
    private String number;
    @Column(nullable = false, name = "Bairro")
    private String district;

    public Customer() {
    }

    public Customer(Long key, String name, String telphone, String zipCode, String street, String number, String district) {
        this.key = key;
        this.name = name;
        this.telphone = telphone;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.district = district;
    }

    public Customer(CustomerDTO customerDTO) {
        this.name = customerDTO.name();
        this.telphone = customerDTO.telphone();
        this.zipCode = customerDTO.zipCode();
        this.street = customerDTO.street();
        this.district = customerDTO.district();
        this.number = customerDTO.number();
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(key, customer.key) && Objects.equals(name, customer.name) && Objects.equals(telphone, customer.telphone) && Objects.equals(zipCode, customer.zipCode) && Objects.equals(street, customer.street) && Objects.equals(number, customer.number) && Objects.equals(district, customer.district);
    }

    public int hashCode() {
        return Objects.hash(key, name, telphone, zipCode, street, number, district);
    }
}
