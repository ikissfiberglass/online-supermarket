package com.atipera.strategy.impl;

import com.atipera.model.Order;
import com.atipera.model.PaymentMethod;
import com.atipera.model.PaymentMethodRegistry;
import com.atipera.model.PaymentResult;
import com.atipera.strategy.PaymentStrategy;

import java.math.BigDecimal;
import java.util.*;

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
        PaymentMethod method = null;
        List<String> promotions = order.getPromotions();

        if (promotions != null && !promotions.isEmpty()) {
            for (String promo : promotions) {
                Optional<PaymentMethod> methodOpt = registry.getPaymentById(promo);
                if (methodOpt.isPresent()) {
                    method = methodOpt.get();
                    break;
                }
            }
        } else {
            List<PaymentMethod> availableMethods = registry.getAll();
            if (!availableMethods.isEmpty()) {
                method = availableMethods.get(0);
            }
        }
        if (Objects.isNull(method)) {
            throw new IllegalArgumentException("No valid payment method found for order: " + order.getId());
        }
        BigDecimal discountAmount = order.getValue()
                .multiply(BigDecimal.valueOf(method.getDiscount()))
                .divide(BigDecimal.valueOf(100));
        BigDecimal amountCharged = order.getValue().subtract(discountAmount);

        return new PaymentResult(true, "Paid by " + method.getId() + " with discount", amountCharged, 0);
    }
}
