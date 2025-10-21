package com.example.decorator_facade_patterns.payment;

public class DiscountDecorator extends PaymentDecorator {
    private double discount;

    public DiscountDecorator(Payment payment, double discount) {
        super(payment);
        this.discount = discount;
    }

    @Override
    public PaymentResult pay(double amount) {
        double discountedAmount = amount * (1 - discount / 100.0);
        PaymentResult result = super.pay(discountedAmount);
        return new PaymentResult(result.isSuccess(), "Discount applied: " + discount + "%. " + result.getMessage());
    }
}
