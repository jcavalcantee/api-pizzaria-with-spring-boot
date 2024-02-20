package com.management.pizzaria.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductOrderKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Long order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Long product;

    public ProductOrderKey() {
    }

    public ProductOrderKey(Long order, Long product) {
        this.order = order;
        this.product = product;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderKey that = (ProductOrderKey) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
