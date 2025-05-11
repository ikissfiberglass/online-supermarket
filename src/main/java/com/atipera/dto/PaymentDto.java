package com.atipera.dto;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String userId;
    private BigDecimal initialAmount;
    private int loyaltyPoints;
    private String discountCode;
    private String paymentMethod; //card, loyalty, mixedz
}
