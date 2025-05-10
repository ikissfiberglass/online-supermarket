package com.atipera;

import com.atipera.model.Order;
import com.atipera.model.PaymentMethod;
import com.atipera.util.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

//TODO add documentation
public class App 
{
    //First arg accepts orders json, second one accepts payment methods file
    public static void main( String[] args ){
        if(args.length < 2){
            System.out.println("App should be provided with 2 arguments: <orders.json> <paymentmethods.json>");
            return;
        }

        try{
            List<Order> orders = JsonDeserializer.deserializeOrders(args[0]);
            List<PaymentMethod> paymentMethods = JsonDeserializer.deserializePaymentMethod(args[1]);

            System.out.println("Orders loaded:\n" + orders.size());
            System.out.println("Payment methods loaded:\n" + paymentMethods.size());
        }catch(IOException ioe){
            System.err.println("An error occured while loading JSON data: " + ioe.getMessage());
        }


    }
}
