package com.management.pizzaria.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable
public class AddressCustomerKey {

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "fk_address_id")
    private Address addressId;

    public AddressCustomerKey() {
    }

    public AddressCustomerKey(Customer customerId, Address addressId) {
        this.customerId = customerId;
        this.addressId = addressId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressCustomerKey that = (AddressCustomerKey) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, addressId);
    }
}
