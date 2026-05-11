package com.penguin.cake.waimai;
import com.penguin.cake.common.ApiResponse;
import com.penguin.cake.middleware.CakeEventBus;
import com.penguin.cake.waimai.dto.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping({"/api/waimai", "/api/takeout"})
public class WaimaiController {
    private final WaimaiAdapterFactory factory; private final CakeEventBus eventBus;
    public WaimaiController(WaimaiAdapterFactory factory, CakeEventBus eventBus){ this.factory = factory; this.eventBus = eventBus; }
    @GetMapping("/platforms") public ApiResponse<List<Map<String,Object>>> platforms(){
        return ApiResponse.ok(List.of(
            Map.of("code","JD_WAIMAI","name","京东外卖","aggregator","歪买外卖聚合平台","mode","Mock + Adapter", "desc", "京东外卖订单接入、商品同步、状态回传"),
            Map.of("code","TAOBAO_SHANGOU","name","淘宝闪购","aggregator","歪买外卖聚合平台","mode","Mock + Adapter", "desc", "淘宝闪购即时零售订单、库存与履约状态适配"),
            Map.of("code","MOCK","name","歪买测试通道","aggregator","歪买外卖聚合平台","mode","Mock", "desc", "本地演示通道，不依赖平台密钥")
        ));
    }
    @PostMapping("/orders/sync") public ApiResponse<WaimaiSyncResponse> sync(@RequestBody WaimaiOrderSyncRequest request){
        WaimaiSyncResponse result = factory.get(request.platform()).receiveOrder(request);
        eventBus.publish("TAKEOUT_ORDER_SYNCED", result.penguinOrderNo(), result);
        return ApiResponse.ok(result);
    }
    @PostMapping("/orders/status") public ApiResponse<WaimaiSyncResponse> status(@RequestBody WaimaiOrderStatusRequest request){
        WaimaiSyncResponse result = factory.get(request.platform()).pushStatus(request);
        eventBus.publish("TAKEOUT_STATUS_PUSHED", request.platformOrderNo(), result);
        return ApiResponse.ok(result);
    }
}
