package com.atipera.service.servicePayment;

import com.atipera.model.Order;

public interface Strategy {
    void pay(Order order );
}
