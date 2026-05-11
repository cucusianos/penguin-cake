package com.penguin.cake.payment.gateway;
import com.penguin.cake.payment.*;
import com.penguin.cake.payment.dto.*;
import java.util.UUID;

public abstract class AbstractDemoPaymentGateway implements PaymentGateway {
    protected PaymentCreateResponse demoResponse(PaymentCreateRequest request, String channel) {
        String paymentNo = channel + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return new PaymentCreateResponse(paymentNo, request.orderNo(), provider(), PaymentStatus.CREATED, request.amount(),
            "https://pay.mock.penguin-cake.local/" + channel + "/" + paymentNo,
            "penguin://pay/" + channel + "/" + paymentNo,
            "DEMO_ONLY: 请在真实环境替换为官方SDK/HTTP调用、证书签名、回调验签");
    }
    public boolean verifyNotify(PaymentNotifyRequest request) { return request != null && request.paymentNo() != null; }
}
