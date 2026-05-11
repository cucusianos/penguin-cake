package com.penguin.cake.middleware;
import com.penguin.cake.common.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping({"/api/cake-middleware", "/api/middleware"})
public class CakeMiddlewareController {
    private final CakeEventBus bus;
    public CakeMiddlewareController(CakeEventBus bus) { this.bus = bus; }
    @GetMapping("/events") public ApiResponse<List<EventEnvelope>> events(){ return ApiResponse.ok(bus.recentEvents()); }
    @GetMapping("/topology") public ApiResponse<Map<String,Object>> topology(){
        return ApiResponse.ok(Map.of(
            "layers", List.of("interface-rest", "application-services", "domain-aggregates", "cake-decoupling-middleware", "infrastructure-adapters"),
            "adapters", List.of("wechat-pay", "alipay", "stripe", "crypto", "meituan", "koubei", "douyin", "jd-waimai", "taobao-shangou", "amap-geo"),
            "patterns", List.of("DDD", "Hexagonal Architecture", "Outbox", "Adapter/Gateway", "Idempotency Key")
        ));
    }
}
