package com.atipera.util;

import com.atipera.model.Order;
import com.atipera.model.PaymentMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonDeserializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Order> deserializeOrders(String path) throws IOException {
        return objectMapper.readValue(new File(path), new TypeReference<List<Order>>() {});
    }

    public static List<PaymentMethod> deserializePaymentMethod(String path) throws IOException{
        return objectMapper.readValue(new File(path), new TypeReference<List<PaymentMethod>>() {});
    }

//    public static <T> List<T> deserializeJson(String path, T listType){
//        return objectMapper.readValue(new File(path), new TypeReference<List<T>>() {});
//    }
}
