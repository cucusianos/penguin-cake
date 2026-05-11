package com.penguin.cake.payment;
import com.penguin.cake.payment.gateway.PaymentGateway;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class PaymentGatewayFactory {
    private final Map<PaymentProvider, PaymentGateway> map = new EnumMap<>(PaymentProvider.class);
    public PaymentGatewayFactory(List<PaymentGateway> gateways) { gateways.forEach(g -> map.put(g.provider(), g)); }
    public PaymentGateway get(PaymentProvider provider) {
        PaymentGateway gateway = map.get(provider);
        if (gateway == null) throw new IllegalArgumentException("Unsupported payment provider: " + provider);
        return gateway;
    }
}
