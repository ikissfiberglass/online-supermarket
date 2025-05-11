package com.atipera.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Setter @Getter
@NoArgsConstructor
public class PaymentMethodRegistry {
    private final Map<String, PaymentMethod> registry = new HashMap<>();

    public void add(PaymentMethod paymentMethod){
        if(Objects.isNull(paymentMethod) || Objects.isNull(paymentMethod.getId())){
            System.out.println("PaymentMethodRegistry received an empty object on add method");
            return;
        }
        registry.put(paymentMethod.getId(), paymentMethod);
    }

    public Optional<PaymentMethod> getPaymentById(String id){
        if(Objects.isNull(id)) return Optional.empty();
        return Optional.ofNullable(registry.get(id));
    }

    public List<PaymentMethod> getAll(){
        return new ArrayList<>(registry.values());
    }
}
