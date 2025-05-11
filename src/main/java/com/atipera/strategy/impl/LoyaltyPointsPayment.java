package com.atipera.strategy.impl;

import com.atipera.model.Order;
import com.atipera.model.PaymentMethod;
import com.atipera.model.PaymentMethodRegistry;
import com.atipera.model.PaymentResult;
import com.atipera.strategy.PaymentStrategy;

import java.math.BigDecimal;

public class LoyaltyPointsPayment implements PaymentStrategy {
    private final PaymentMethodRegistry registry;

    public LoyaltyPointsPayment(PaymentMethodRegistry registry) {
        this.registry = registry;
    }

    @Override
    public boolean supports(String method){
        return registry.getPaymentById(method).isPresent();
    }

    @Override
    public PaymentResult pay(Order order) {
        //TODO add not-null check

        PaymentMethod method = registry.getPaymentById("PUNKTY").
                orElseThrow(() -> new IllegalArgumentException("Payment method 'LOYALTY_POINTS' not found in registry"));
        BigDecimal discountAmount = order.getValue().multiply(BigDecimal.valueOf(method.getDiscount())).divide(BigDecimal.valueOf(100));

        boolean isValidForExtraDiscount = order.getLoyaltyPointsUsed() >= order.getValue().multiply(BigDecimal.valueOf(0.1)).intValue();
        if(isValidForExtraDiscount) discountAmount = discountAmount.add(order.getValue().multiply(BigDecimal.valueOf(0.1)));

        BigDecimal amountCharged = order.getValue().subtract(discountAmount);
        return new PaymentResult(true, "Paid using loyalty points with discount", amountCharged, order.getLoyaltyPointsUsed());
    }
}
