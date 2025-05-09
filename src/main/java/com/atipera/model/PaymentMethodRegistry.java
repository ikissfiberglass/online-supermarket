package com.atipera.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter @Getter
@NoArgsConstructor
public class PaymentMethodRegistry {
    private Map<String, PaymentMethod> registry;

    public PaymentMethodRegistry(Map<String, PaymentMethod> registry){
        this.registry = registry;
    }

    public void putSafely(PaymentMethod paymentMethod){
        if(Objects.isNull(paymentMethod) || Objects.isNull(paymentMethod.getId())){
            System.out.println("PaymentMethodRegistry received an empty object on putSafely method");
            return;
        }
        registry.put(paymentMethod.getId(), paymentMethod);
    }

    public Optional<PaymentMethod> getPaymentById(String id){
        if(Objects.isNull(id)) return Optional.empty();
        return Optional.ofNullable(registry.get(id));
    }

    //TODO getAll method
    public List<PaymentMethod> getAll(){
        return registry.values().
                stream().collect(Collectors.toList());
    }

}
