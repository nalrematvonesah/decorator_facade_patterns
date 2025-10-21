package com.example.decorator_facade_patterns.dto;

public class OrderRequestDto {
    private String paymentMethod;
    private Double amount;
    private String customerName;
    private String customerEmail;

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}
