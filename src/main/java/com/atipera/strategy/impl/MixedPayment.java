package com.atipera.strategy.impl;

import com.atipera.model.Order;
import com.atipera.model.PaymentMethod;
import com.atipera.model.PaymentMethodRegistry;
import com.atipera.model.PaymentResult;
import com.atipera.strategy.PaymentStrategy;

import java.math.BigDecimal;

public class MixedPayment implements PaymentStrategy {
    private final PaymentMethodRegistry registry;

    public MixedPayment(PaymentMethodRegistry registry) {
        this.registry = registry;
    }


    @Override
    public boolean supports(String method) {
        return registry.getPaymentById(method).isPresent();
    }

    @Override
    public PaymentResult pay(Order order) {
        PaymentMethod pointsMethod = registry.getPaymentById("PUNKTY").
                orElseThrow(() -> new IllegalArgumentException("Payment method 'LOYALTY_POINTS' not found in registry"));
        PaymentMethod cardMethod = registry.getPaymentById("CARD")
                .orElseThrow(() -> new IllegalArgumentException("Payment method 'CARD' not found in registry"));

        int pointsUsed = Math.min(order.getLoyaltyPointsUsed(), order.getValue().multiply(BigDecimal.valueOf(0.1)).intValue());
        BigDecimal pointsDiscount = order.getValue().multiply(BigDecimal.valueOf(0.1));

        BigDecimal remainingAmount = order.getValue().subtract(pointsDiscount);
        BigDecimal cardDiscount = remainingAmount.multiply(BigDecimal.valueOf(cardMethod.getDiscount())).divide(BigDecimal.valueOf(100));
        BigDecimal finalAmountCharged = remainingAmount.subtract(cardDiscount);

        return new PaymentResult(true, "Paid using mixed method with discounts", finalAmountCharged, pointsUsed);
    }
}

