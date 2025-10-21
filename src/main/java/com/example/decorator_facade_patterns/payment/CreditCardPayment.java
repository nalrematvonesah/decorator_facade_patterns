package com.example.decorator_facade_patterns.payment;

public class CreditCardPayment implements Payment {
    public PaymentResult pay(double amount) {
        return new PaymentResult(true, "Paid " + amount + " using Credit Card.");
    }
}
