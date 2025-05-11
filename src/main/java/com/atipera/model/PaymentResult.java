package com.atipera.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
//@AllArgsConstructor
@NoArgsConstructor
public class PaymentResult {
    private boolean success;
    private String message;
    private BigDecimal amountCharged;
    private int pointsUsed;
    private String paymentMethodId;

    public PaymentResult(boolean success, String message, BigDecimal amountCharged, int pointsUsed) {
        this.success = success;
        this.message = message;
        this.amountCharged = amountCharged;
        this.pointsUsed = pointsUsed;
    }
}