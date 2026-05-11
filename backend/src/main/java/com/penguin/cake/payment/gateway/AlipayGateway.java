package com.penguin.cake.payment.gateway;
import com.penguin.cake.payment.PaymentProvider;
import com.penguin.cake.payment.dto.*;
import org.springframework.stereotype.Component;
@Component
public class AlipayGateway extends AbstractDemoPaymentGateway {
    public PaymentProvider provider() { return PaymentProvider.ALIPAY; }
    public PaymentCreateResponse create(PaymentCreateRequest request) { return demoResponse(request, "alipay"); }
}
