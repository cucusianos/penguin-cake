package com.penguin.cake.takeout;
import com.penguin.cake.takeout.adapter.TakeoutAdapter;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class TakeoutAdapterFactory {
    private final Map<TakeoutPlatform, TakeoutAdapter> map = new EnumMap<>(TakeoutPlatform.class);
    public TakeoutAdapterFactory(List<TakeoutAdapter> adapters) { adapters.forEach(a -> map.put(a.platform(), a)); }
    public TakeoutAdapter get(TakeoutPlatform platform) { return Optional.ofNullable(map.get(platform)).orElseThrow(); }
}
