package com.atipera.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Order {
    private String id;
    private BigDecimal value;
    private List<String> promotions;
    private int loyaltyPointsUsed;
    private BigDecimal finalAmount;

    public Order(String id, BigDecimal value, List<String> promotions, int loyaltyPointsUsed, BigDecimal finalAmount) {
        this.id = id;
        this.value = value;
        this.promotions = promotions;
        this.loyaltyPointsUsed = loyaltyPointsUsed;
        this.finalAmount = finalAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public List<String> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<String> promotions) {
        this.promotions = promotions;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", promotions=" + promotions +
                '}';
    }
}