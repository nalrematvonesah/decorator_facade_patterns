package com.example.decorator_facade_patterns.payment;

public abstract class PaymentDecorator implements Payment {
    protected Payment payment;

    public PaymentDecorator(Payment payment) {
        this.payment = payment;
    }

    @Override
    public PaymentResult pay(double amount) {
        return payment.pay(amount);
    }
}
