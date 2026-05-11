package com.penguin.cake.voucher.adapter;
import com.penguin.cake.voucher.VoucherChannel;
import org.springframework.stereotype.Component;
@Component
public class KoubeiVoucherAdapter extends AbstractDemoVoucherAdapter {
    public VoucherChannel channel() { return VoucherChannel.KOUBEI; }
}
