package com.management.pizzaria.models;

import com.management.pizzaria.enums.PaymentType;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TB_ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long orderId;
    @Column(name = "Tipo_Pagamento")
    private PaymentType paymentType;
    @Column(name = "Data_Pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    public Long getOrderId() {
        return orderId;
    }

    public Order() {
    }

    public Order(Long orderId, Date orderDate, PaymentType paymentType) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.paymentType = paymentType;
    }

    public void setOrderId(Long paymentId) {
        this.orderId = paymentId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(orderDate, order.orderDate) && paymentType == order.paymentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, paymentType);
    }
}
