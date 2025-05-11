package com.atipera;

import com.atipera.model.Order;
import com.atipera.model.PaymentMethod;
import com.atipera.model.PaymentMethodRegistry;
import com.atipera.processor.PaymentProcessor;
import com.atipera.service.PaymentService;
import com.atipera.strategy.PaymentStrategy;
import com.atipera.strategy.impl.CardPayment;
import com.atipera.strategy.impl.LoyaltyPointsPayment;
import com.atipera.strategy.impl.MixedPayment;
import com.atipera.util.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//TODO add documentation
public class App 
{
    //First arg accepts orders json, second one accepts payment methods file
    public static void main( String[] args ){
        if(args.length < 2){
            System.err.println("App should be runned with 2 arguments provided: <orders.json> <paymentmethods.json>");
        }

        try{
            List<Order> orders = JsonDeserializer.deserializeOrders(args[0]);
            List<PaymentMethod> methods = JsonDeserializer.deserializePaymentMethod(args[1]);

            PaymentMethodRegistry registry = new PaymentMethodRegistry();

            methods.forEach(method -> registry.add(method));

            List<PaymentStrategy> strategies = Arrays.asList(
                    new CardPayment(registry),
                    new LoyaltyPointsPayment(registry),
                    new MixedPayment(registry)
            );

            PaymentProcessor processor = new PaymentProcessor(strategies, registry);
            PaymentService paymentService = new PaymentService(processor);
            paymentService.processOrders(orders);
        }catch (IOException ioe){
            System.err.println("Error processing payments: " + ioe.getMessage());
        }
    }
}
