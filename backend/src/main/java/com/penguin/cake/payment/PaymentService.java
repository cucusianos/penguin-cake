package com.penguin.cake.payment;
import com.penguin.cake.middleware.CakeEventBus;
import com.penguin.cake.payment.dto.*;
import org.springframework.stereotype.Service;
@Service
public class PaymentService {
    private final PaymentGatewayFactory factory;
    private final CakeEventBus eventBus;
    public PaymentService(PaymentGatewayFactory factory, CakeEventBus eventBus) { this.factory = factory; this.eventBus = eventBus; }
    public PaymentCreateResponse create(PaymentCreateRequest request) {
        PaymentCreateResponse response = factory.get(request.provider()).create(request);
        eventBus.publish("PAYMENT_CREATED", response.orderNo(), response);
        return response;
    }
    public boolean notify(PaymentNotifyRequest request) {
        boolean ok = factory.get(request.provider()).verifyNotify(request);
        if (ok) eventBus.publish("PAYMENT_NOTIFY_VERIFIED", request.orderNo(), request);
        return ok;
    }
}
