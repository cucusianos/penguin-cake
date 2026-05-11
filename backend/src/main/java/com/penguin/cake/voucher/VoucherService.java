package com.penguin.cake.voucher;
import com.penguin.cake.middleware.CakeEventBus;
import com.penguin.cake.voucher.dto.*;
import org.springframework.stereotype.Service;
@Service
public class VoucherService {
    private final VoucherAdapterFactory factory;
    private final CakeEventBus eventBus;
    public VoucherService(VoucherAdapterFactory factory, CakeEventBus eventBus) { this.factory = factory; this.eventBus = eventBus; }
    public VoucherVerifyResponse verify(VoucherVerifyRequest request) {
        VoucherVerifyResponse result = factory.get(request.channel()).verify(request);
        eventBus.publish("VOUCHER_VERIFIED", request.code(), result);
        return result;
    }
    public VoucherVerifyResponse consume(VoucherConsumeRequest request) {
        VoucherVerifyResponse result = factory.get(request.channel()).consume(request);
        eventBus.publish("VOUCHER_CONSUMED", request.code(), result);
        return result;
    }
}
