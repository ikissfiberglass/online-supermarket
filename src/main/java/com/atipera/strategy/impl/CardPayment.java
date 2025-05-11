package com.atipera.strategy.impl;

import com.atipera.model.Order;
import com.atipera.model.PaymentMethod;
import com.atipera.model.PaymentMethodRegistry;
import com.atipera.model.PaymentResult;
import com.atipera.strategy.PaymentStrategy;

import java.math.BigDecimal;

public class CardPayment implements PaymentStrategy {
    private final PaymentMethodRegistry registry;

    public CardPayment(PaymentMethodRegistry registry){
        this.registry = registry;
    }

    @Override
    public boolean supports(String method){
        return registry.getPaymentById(method).isPresent();
    }

    @Override
    public PaymentResult pay(Order order) {
//        return new PaymentResult(true, "Paid by card", order.getValue(), 0);
        PaymentMethod method = registry.getPaymentById("CARD")
                .orElseThrow(() -> new IllegalArgumentException("Payment method 'CARD' not found in registry"));
        BigDecimal discountAmount = order.getValue().multiply(BigDecimal.valueOf(method.getDiscount())).divide(BigDecimal.valueOf(100));
        BigDecimal amountCharged = order.getValue().subtract(discountAmount);

        return new PaymentResult(true, "PAid by card with discount", amountCharged, 0);
    }
}
