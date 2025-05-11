package com.atipera.service;

import com.atipera.model.Order;
import com.atipera.model.PaymentResult;
import com.atipera.processor.PaymentProcessor;

import java.util.List;

public class PaymentService {
    private final PaymentProcessor processor;

    public PaymentService(PaymentProcessor processor){
        this.processor = processor;
    }

    public void processOrders(List<Order> orders){
        orders.forEach(order -> {
            PaymentResult result = processor.process(order);
            System.out.println("Order " + order.getId() + "processed with: " + result.getMessage());
        });
    }
}
