package com.example.decorator_facade_patterns.service;

import com.example.decorator_facade_patterns.entity.Customer;
import com.example.decorator_facade_patterns.entity.Order;
import com.example.decorator_facade_patterns.entity.PaymentRecord;
import com.example.decorator_facade_patterns.payment.*;
import com.example.decorator_facade_patterns.repository.CustomerRepository;
import com.example.decorator_facade_patterns.repository.OrderRepository;
import com.example.decorator_facade_patterns.repository.PaymentRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckoutFacade {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRecordRepository paymentRecordRepository;

    @Transactional
    public PaymentResult processOrder(Order order, Payment payment, Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        order.setCustomer(savedCustomer);


        Payment decoratedPayment = new DiscountDecorator(payment, 10);
        decoratedPayment = new CashbackDecorator(decoratedPayment);
        decoratedPayment = new FraudDetectionDecorator(decoratedPayment);


        PaymentResult result = decoratedPayment.pay(order.getAmount());


        orderRepository.save(order);

        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setAmount(order.getAmount());
        paymentRecord.setPaymentType(payment.getClass().getSimpleName());
        paymentRecord.setOrder(order);
        paymentRecordRepository.save(paymentRecord);

        return new PaymentResult(true, "Order processed successfully. " + result.getMessage());
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order with id " + id + " not found");
        }

        paymentRecordRepository.deleteByOrderId(id);

        orderRepository.deleteById(id);
    }
}
