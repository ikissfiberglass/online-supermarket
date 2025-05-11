package com.atipera.processor;

import com.atipera.dto.PaymentDto;
import com.atipera.model.Order;
import com.atipera.model.PaymentMethodRegistry;
import com.atipera.model.PaymentResult;
import com.atipera.strategy.PaymentStrategy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class PaymentProcessor {
    private final List<PaymentStrategy> strategies;
    private final PaymentMethodRegistry registry;

    public PaymentProcessor(List<PaymentStrategy> strategies, PaymentMethodRegistry registry) {
        this.strategies = strategies;
        this.registry = registry;
    }

    public PaymentResult process(Order order){
        registry.getAll().forEach(method -> System.out.println("Registered payment method: " + method.getId()));

        String bestMethod = findBestPaymentMethod(order);

        return strategies.stream()
                .filter(strategy -> strategy.supports(bestMethod))
                .findFirst()
                .map(strategy -> strategy.pay(order))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported payment operation: " + bestMethod));
    }

    private String findBestPaymentMethod(Order order){
        List<String> promotions = order.getPromotions();

        if(registry.getPaymentById("PUNKTY").isPresent() &&
                order.getLoyaltyPointsUsed() >= order.getValue().multiply(BigDecimal.valueOf(0.1)).intValue()){
            return "LOYALITY_POINTS";
        }

        return promotions.stream()
                .filter(promotion -> registry.getPaymentById(promotion).isPresent())
                .findFirst()
                .orElse("CARD");
    }

}
