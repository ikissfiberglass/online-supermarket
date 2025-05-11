package com.atipera.convertor;

import com.atipera.dto.PaymentDto;
import com.atipera.model.Order;
import com.atipera.model.PaymentMethods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PaymentConvertor {
    public static Order mapToOrder(PaymentDto dto){
        List<String> promotionsList = new ArrayList<>();
        if(!Objects.isNull(dto.getDiscountCode()) && !dto.getDiscountCode().isEmpty()){
            promotionsList.add(dto.getDiscountCode());
        }
        BigDecimal tempZero = BigDecimal.valueOf(0);

        return new Order(
                "ORDER" + UUID.randomUUID(),
                dto.getInitialAmount(),
                promotionsList,
                dto.getLoyaltyPoints(),
                tempZero);
    }
}
