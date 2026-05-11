package com.penguin.cake.voucher.dto;
import com.penguin.cake.voucher.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public record VoucherVerifyResponse(String code, VoucherChannel channel, VoucherStatus status, String productName, BigDecimal faceValue, LocalDateTime expireAt, String message) {}
