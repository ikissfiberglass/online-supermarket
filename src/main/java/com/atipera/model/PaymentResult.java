package com.atipera.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResult {
    private boolean success;
    private String message;
    private BigDecimal amountCharged;
    private int pointsUsed;
}