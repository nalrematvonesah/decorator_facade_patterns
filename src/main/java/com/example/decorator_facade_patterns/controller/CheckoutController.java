package com.example.decorator_facade_patterns.controller;

import com.example.decorator_facade_patterns.dto.OrderRequestDto;
import com.example.decorator_facade_patterns.entity.Customer;
import com.example.decorator_facade_patterns.entity.Order;
import com.example.decorator_facade_patterns.payment.*;
import com.example.decorator_facade_patterns.service.CheckoutFacade;
import com.example.decorator_facade_patterns.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutFacade checkoutFacade;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/")
    public ResponseEntity<PaymentResult> checkout(@RequestBody OrderRequestDto dto) {
        Payment payment;

        switch (dto.getPaymentMethod()) {
            case "credit_card" -> payment = new CreditCardPayment();
            case "paypal" -> payment = new PayPalPayment();
            default -> throw new IllegalArgumentException("Unsupported payment method: " + dto.getPaymentMethod());
        }

        Customer customer = new Customer();
        customer.setName(dto.getCustomerName());

        Order order = new Order();
        order.setAmount(dto.getAmount());

        PaymentResult result = checkoutFacade.processOrder(order, payment, customer);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto dto) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setAmount(dto.getAmount());
                    if (order.getCustomer() == null) {
                        order.setCustomer(new Customer());
                    }
                    order.getCustomer().setName(dto.getCustomerName());
                    orderRepository.save(order);
                    return ResponseEntity.ok(order);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            checkoutFacade.deleteOrder(id);
            return ResponseEntity.noContent().build(); // 204 success
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();  // 404 if order not found
        }
    }
}
