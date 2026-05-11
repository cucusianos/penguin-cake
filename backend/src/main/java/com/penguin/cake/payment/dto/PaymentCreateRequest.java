package com.penguin.cake.payment.dto;
import com.penguin.cake.payment.PaymentProvider;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record PaymentCreateRequest(@NotBlank String orderNo, @NotNull PaymentProvider provider, @NotNull BigDecimal amount, String subject, String returnUrl, String notifyUrl) {}
