package com.atipera.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
public class Order {
    private String id;
    private BigDecimal value;
    private List<String> promotions = new ArrayList<>();
    //private static HashSet<String> existingPromotions = new HashSet<>();

    public Order(String id, BigDecimal value){
        this.id = id;
        this.value = value;
    }
}

/*[
  {
    "id": "ORDER1",
    "value": "100.00",
    "promotions": [
      "mZysk"
    ]
  },
  {
    "id": "ORDER2",
    "value": "200.00",
    "promotions": [
      "BosBankrut"
    ]
  },
  {
    "id": "ORDER3",
    "value": "150.00",
    "promotions": [
      "mZysk",
      "BosBankrut"
    ]
  },
  {
    "id": "ORDER4",
    "value": "50.00"
  }
]*/