package com.penguin.cake.takeout;
import com.penguin.cake.common.ApiResponse;
import com.penguin.cake.middleware.CakeEventBus;
import com.penguin.cake.takeout.dto.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping({"/api/takeout", "/api/takeout"})
public class TakeoutController {
    private final TakeoutAdapterFactory factory; private final CakeEventBus eventBus;
    public TakeoutController(TakeoutAdapterFactory factory, CakeEventBus eventBus){ this.factory = factory; this.eventBus = eventBus; }
    @GetMapping("/platforms") public ApiResponse<List<Map<String,Object>>> platforms(){
        return ApiResponse.ok(List.of(
            Map.of("code","JD_TAKEOUT","name","京东外卖","aggregator","外卖聚合平台","mode","Mock + Adapter", "desc", "京东外卖订单接入、商品同步、状态回传"),
            Map.of("code","TAOBAO_SHANGOU","name","淘宝闪购","aggregator","外卖聚合平台","mode","Mock + Adapter", "desc", "淘宝闪购即时零售订单、库存与履约状态适配"),
            Map.of("code","MOCK","name","外卖测试通道","aggregator","外卖聚合平台","mode","Mock", "desc", "本地演示通道，不依赖平台密钥")
        ));
    }
    @PostMapping("/orders/sync") public ApiResponse<TakeoutSyncResponse> sync(@RequestBody TakeoutOrderSyncRequest request){
        TakeoutSyncResponse result = factory.get(request.platform()).receiveOrder(request);
        eventBus.publish("TAKEOUT_ORDER_SYNCED", result.penguinOrderNo(), result);
        return ApiResponse.ok(result);
    }
    @PostMapping("/orders/status") public ApiResponse<TakeoutSyncResponse> status(@RequestBody TakeoutOrderStatusRequest request){
        TakeoutSyncResponse result = factory.get(request.platform()).pushStatus(request);
        eventBus.publish("TAKEOUT_STATUS_PUSHED", request.platformOrderNo(), result);
        return ApiResponse.ok(result);
    }
}
