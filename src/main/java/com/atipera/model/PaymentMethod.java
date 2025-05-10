package com.atipera.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PaymentMethod {
    private String id;
    private int discount;
    private BigDecimal limit;

    public String getId() {
        return id;
    }
}
