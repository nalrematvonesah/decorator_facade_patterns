package com.example.decorator_facade_patterns.payment;

public class FraudDetectionDecorator extends PaymentDecorator {
    public FraudDetectionDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public PaymentResult pay(double amount) {
        PaymentResult result = super.pay(amount);
        return new PaymentResult(result.isSuccess(), result.getMessage() + " Fraud detection passed.");
    }
}
