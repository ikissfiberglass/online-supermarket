package com.atipera.service.Payment;

import com.atipera.model.Order;

public interface Strategy {
    void pay(Order order );
}
