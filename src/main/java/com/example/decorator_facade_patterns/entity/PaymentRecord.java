package com.example.decorator_facade_patterns.entity;

import jakarta.persistence.*;

@Entity
public class PaymentRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentType;
    private Double amount;
    @ManyToOne
    private Order order;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
