package com.penguin.cake.voucher.adapter;
import com.penguin.cake.voucher.VoucherChannel;
import org.springframework.stereotype.Component;
@Component
public class DouyinVoucherAdapter extends AbstractDemoVoucherAdapter {
    public VoucherChannel channel() { return VoucherChannel.DOUYIN; }
}
