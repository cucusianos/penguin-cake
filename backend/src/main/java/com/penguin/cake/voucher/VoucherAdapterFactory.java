package com.penguin.cake.voucher;
import com.penguin.cake.voucher.adapter.VoucherAdapter;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class VoucherAdapterFactory {
    private final Map<VoucherChannel, VoucherAdapter> map = new EnumMap<>(VoucherChannel.class);
    public VoucherAdapterFactory(List<VoucherAdapter> adapters) { adapters.forEach(a -> map.put(a.channel(), a)); }
    public VoucherAdapter get(VoucherChannel channel) { return Optional.ofNullable(map.get(channel)).orElseThrow(); }
}
