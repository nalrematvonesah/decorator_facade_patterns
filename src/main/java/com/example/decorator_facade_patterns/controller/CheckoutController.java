package com.example.decorator_facade_patterns.controller;

import com.example.decorator_facade_patterns.dto.OrderRequestDto;
import com.example.decorator_facade_patterns.entity.Customer;
import com.example.decorator_facade_patterns.entity.Order;
import com.example.decorator_facade_patterns.payment.*;
import com.example.decorator_facade_patterns.service.CheckoutFacade;
import com.example.decorator_facade_patterns.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // POST: Place order + customer + payment processing
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

    // GET: List all orders
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // GET: Get order by ID
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Update order (partial, e.g., amount)
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setAmount(updatedOrder.getAmount());
                    orderRepository.save(order);
                    return ResponseEntity.ok(order);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
