package com.management.pizzaria.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class AddressCustomer {

    @EmbeddedId
    private AddressCustomerKey id;

    public AddressCustomer() {
    }

    public AddressCustomer(AddressCustomerKey id) {
        this.id = id;
    }

    public AddressCustomerKey getId() {
        return id;
    }

    public void setId(AddressCustomerKey id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressCustomer that = (AddressCustomer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
