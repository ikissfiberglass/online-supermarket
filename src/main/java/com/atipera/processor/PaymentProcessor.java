package com.atipera.processor;

import com.atipera.dto.PaymentDto;
import com.atipera.model.Order;
import com.atipera.model.PaymentMethod;
import com.atipera.model.PaymentMethodRegistry;
import com.atipera.model.PaymentResult;
import com.atipera.strategy.PaymentStrategy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

public class PaymentProcessor {
    private final List<PaymentStrategy> strategies;
    private final PaymentMethodRegistry registry;

    public PaymentProcessor(List<PaymentStrategy> strategies, PaymentMethodRegistry registry) {
        this.strategies = strategies;
        this.registry = registry;
    }

    public PaymentResult process(Order order){
        String bestMethod = findBestPaymentMethod(order);
        System.out.println("Best payment method: " + bestMethod + " for order #" + order.getId());
        return strategies.stream()
                .filter(strategy -> strategy.supports(bestMethod))
                .findFirst()
                .map(strategy -> {
                    System.out.println("Using payment strategy: " + strategy.getClass().getSimpleName());
                    return strategy.pay(order);
                })
                .orElseThrow(() -> new IllegalArgumentException("Unsupported payment operation: " + bestMethod));
    }

    private String findBestPaymentMethod(Order order) {
        if (!Objects.isNull(order.getPromotions()) && !order.getPromotions().isEmpty()) {
            for (String promotion : order.getPromotions()) {
                if (registry.getPaymentById(promotion).isPresent()) {
                    return promotion;
                }
            }
        }

        if (registry.getPaymentById("PUNKTY").isPresent() &&
                order.getLoyaltyPointsUsed() >= order.getValue().multiply(BigDecimal.valueOf(0.1)).intValue()) {
            return "PUNKTY";
        }

        List<PaymentMethod> availableMethods = registry.getAll();
        if (!availableMethods.isEmpty()) {
            return availableMethods.get(0).getId();
        }

        System.err.println("No valid payment methods available for order: " + order.getId());
        throw new IllegalArgumentException("No valid payment methods available for order: " + order.getId());
    }

    public void printRegisteredPaymentMethods() {
        registry.getAll().forEach(method ->
                System.out.println("Registered payment method: " + method.getId()));
    }
}
