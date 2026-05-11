package com.penguin.cake.waimai;
import com.penguin.cake.waimai.adapter.WaimaiAdapter;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class WaimaiAdapterFactory {
    private final Map<WaimaiPlatform, WaimaiAdapter> map = new EnumMap<>(WaimaiPlatform.class);
    public WaimaiAdapterFactory(List<WaimaiAdapter> adapters) { adapters.forEach(a -> map.put(a.platform(), a)); }
    public WaimaiAdapter get(WaimaiPlatform platform) { return Optional.ofNullable(map.get(platform)).orElseThrow(); }
}
