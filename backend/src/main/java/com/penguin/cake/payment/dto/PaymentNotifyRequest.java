package com.penguin.cake.payment.dto;
import com.penguin.cake.payment.PaymentProvider;
import java.util.Map;
public record PaymentNotifyRequest(PaymentProvider provider, String paymentNo, String orderNo, String status, Map<String,Object> rawBody) {}
