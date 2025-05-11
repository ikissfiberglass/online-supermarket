package com.atipera.util;

import com.atipera.model.Order;
import com.atipera.model.PaymentResult;
import com.atipera.processor.PaymentProcessor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentSummaryPrinter {
    private final PaymentProcessor paymentProcessor;

    public PaymentSummaryPrinter(PaymentProcessor paymentProcessor){
        this.paymentProcessor = paymentProcessor;
    }

    public void printSummary(List<Order> orders){
        Map<String, BigDecimal> paymentSummary = new HashMap<>();

        for(Order order : orders){
            PaymentResult res = paymentProcessor.process(order);
            String paymentMethodId = retrievePaymentMethod(res.getMessage());
            BigDecimal amountSpent = res.getAmountCharged();

            paymentSummary.put(paymentMethodId, paymentSummary.getOrDefault(paymentMethodId, BigDecimal.ZERO).add(amountSpent));
        }
        paymentSummary.forEach((method, amount) -> System.out.println(method + " " + amount + " \n"));
    }

    private String retrievePaymentMethod(String messagr){
        String[] words = messagr.split(" ");
        if(words.length < 3){
            throw new IllegalArgumentException("Unexprected payment result format: " + messagr);
        }
        return words[2];
    }
}
