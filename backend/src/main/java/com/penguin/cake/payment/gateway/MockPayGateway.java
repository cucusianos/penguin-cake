package com.penguin.cake.payment.gateway;
import com.penguin.cake.payment.PaymentProvider;
import com.penguin.cake.payment.dto.*;
import org.springframework.stereotype.Component;
@Component
public class MockPayGateway extends AbstractDemoPaymentGateway {
    public PaymentProvider provider() { return PaymentProvider.MOCK; }
    public PaymentCreateResponse create(PaymentCreateRequest request) { return demoResponse(request, "mock"); }
}
