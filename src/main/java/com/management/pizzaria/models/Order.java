package com.management.pizzaria.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TB_ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long paymentId;
    @Column(name = "Data_Pedido")
    private Date orderDate;
    @Column(name = "Tipo_Pagamento")
    private PaymentType paymentType;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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
        return Objects.equals(paymentId, order.paymentId) && Objects.equals(orderDate, order.orderDate) && paymentType == order.paymentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, orderDate, paymentType);
    }
}
