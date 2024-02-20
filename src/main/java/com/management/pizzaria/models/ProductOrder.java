package com.management.pizzaria.models;

import com.management.pizzaria.dtos.ProductOrderDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_PRODUCT_ORDERS")
public class ProductOrder {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "order_id")
    private Long order;
    @JoinColumn(name = "product_id")
    private Long product;
    @Column(nullable = false, name = "Quantidade")
    private int quantity;

    public ProductOrder() {
    }
    public ProductOrder(Long order, Long product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;

    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
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
        return Objects.equals(order, that.order) && Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product, quantity);
    }
}
