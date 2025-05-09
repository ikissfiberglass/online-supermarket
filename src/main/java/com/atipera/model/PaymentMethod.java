package com.atipera.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
public class PaymentMethod {
    private String id;
    private int discount;
    private BigDecimal limit;

}
/*[
  {
    "id": "PUNKTY",
    "discount": "15",
    "limit": "100.00"
  },
  {
    "id": "mZysk",
    "discount": "10",
    "limit": "180.00"
  },
  {
    "id": "BosBankrut",
    "discount": "5",
    "limit": "200.00"
  }
]*/