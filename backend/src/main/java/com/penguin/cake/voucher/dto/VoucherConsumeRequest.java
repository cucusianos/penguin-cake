package com.penguin.cake.voucher.dto;
import com.penguin.cake.voucher.VoucherChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record VoucherConsumeRequest(@NotNull VoucherChannel channel, @NotBlank String code, String storeId, String operatorId, String remark) {}
