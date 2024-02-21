package com.management.pizzaria.models;

import com.management.pizzaria.dtos.ProductOrderDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_PRODUCT_ORDERS")
public class ProductOrder {

    @EmbeddedId
    private ProductOrderKey id;
    @Column(nullable = false, name = "Quantidade")
    private int quantity;

    public ProductOrder() {
    }

    public ProductOrder(ProductOrderKey id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public ProductOrderKey getId() {
        return id;
    }

    public void setId(ProductOrderKey id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return quantity == that.quantity && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }
}
