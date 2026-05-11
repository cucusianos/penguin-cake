package com.penguin.cake.payment.dto;
import com.penguin.cake.payment.*;
import java.math.BigDecimal;
public record PaymentCreateResponse(String paymentNo, String orderNo, PaymentProvider provider, PaymentStatus status, BigDecimal amount, String payUrl, String qrCode, String rawPayload) {}
