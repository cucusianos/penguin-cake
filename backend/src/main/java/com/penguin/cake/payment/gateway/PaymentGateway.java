package com.penguin.cake.payment.gateway;
import com.penguin.cake.payment.PaymentProvider;
import com.penguin.cake.payment.dto.*;
public interface PaymentGateway {
    PaymentProvider provider();
    PaymentCreateResponse create(PaymentCreateRequest request);
    boolean verifyNotify(PaymentNotifyRequest request);
}
