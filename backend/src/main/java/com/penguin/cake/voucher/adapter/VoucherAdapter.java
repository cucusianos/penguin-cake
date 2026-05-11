package com.penguin.cake.voucher.adapter;
import com.penguin.cake.voucher.VoucherChannel;
import com.penguin.cake.voucher.dto.*;
public interface VoucherAdapter {
    VoucherChannel channel();
    VoucherVerifyResponse verify(VoucherVerifyRequest request);
    VoucherVerifyResponse consume(VoucherConsumeRequest request);
}
