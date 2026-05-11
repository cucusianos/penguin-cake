package com.penguin.cake.voucher.adapter;
import com.penguin.cake.voucher.*;
import com.penguin.cake.voucher.dto.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public abstract class AbstractDemoVoucherAdapter implements VoucherAdapter {
    protected VoucherVerifyResponse demo(String code, VoucherStatus status, String msg) {
        return new VoucherVerifyResponse(code, channel(), status, "企鹅蛋糕 100 元团购券", new BigDecimal("100.00"), LocalDateTime.now().plusDays(30), msg);
    }
    public VoucherVerifyResponse verify(VoucherVerifyRequest request) { return demo(request.code(), VoucherStatus.VALID, "验券通过，Demo通道"); }
    public VoucherVerifyResponse consume(VoucherConsumeRequest request) { return demo(request.code(), VoucherStatus.CONSUMED, "核销成功，Demo通道"); }
}
