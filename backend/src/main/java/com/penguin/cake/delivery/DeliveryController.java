package com.penguin.cake.delivery;
import com.penguin.cake.common.ApiResponse;
import com.penguin.cake.middleware.CakeEventBus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    private final CakeEventBus eventBus;
    public DeliveryController(CakeEventBus eventBus) { this.eventBus = eventBus; }
    @GetMapping("/tasks") public ApiResponse<List<Map<String,Object>>> tasks(){
        return ApiResponse.ok(List.of(
            Map.of("taskNo","DLV20260511001","orderNo","QG202605110001","city","广州","rider","陈师傅","status","配送中","lng",113.330,"lat",23.135,"temperature","4°C"),
            Map.of("taskNo","DLV20260511002","orderNo","JDWM20260511018","city","深圳","rider","李师傅","status","已取货","lng",114.060,"lat",22.545,"temperature","5°C"),
            Map.of("taskNo","DLV20260511003","orderNo","TBSG20260511022","city","佛山","rider","周师傅","status","等待取货","lng",113.122,"lat",23.021,"temperature","4°C")
        ));
    }
    @PostMapping("/locations") public ApiResponse<Map<String,Object>> location(@RequestBody Map<String,Object> body){
        Map<String,Object> result = new LinkedHashMap<>(body); result.put("receivedAt", LocalDateTime.now());
        eventBus.publish("DELIVERY_LOCATION_REPORTED", String.valueOf(body.getOrDefault("taskNo","UNKNOWN")), result);
        return ApiResponse.ok("位置上报成功", result);
    }
}
