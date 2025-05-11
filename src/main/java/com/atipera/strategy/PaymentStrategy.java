package com.atipera.strategy;

import com.atipera.model.Order;
import com.atipera.model.PaymentResult;

public interface PaymentStrategy {
    boolean supports(String method);
    PaymentResult pay(Order order );
}
