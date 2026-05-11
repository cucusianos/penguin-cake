package com.penguin.cake.warehouse;
import com.penguin.cake.common.ApiResponse;
import com.penguin.cake.middleware.CakeEventBus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    private final CakeEventBus eventBus;
    public WarehouseController(CakeEventBus eventBus) { this.eventBus = eventBus; }
    @GetMapping("/summary") public ApiResponse<Map<String,Object>> summary(){
        return ApiResponse.ok(Map.of("totalStock", 12860, "coldStorage", "4°C", "damageToday", 7, "nearExpiry", 42, "centerWarehouse", "广州番禺中央冷链仓"));
    }
    @GetMapping("/cake-status") public ApiResponse<List<Map<String,Object>>> cakeStatus(){
        return ApiResponse.ok(List.of(
            Map.of("batchNo","CAKE-20260511-001","name","企鹅莓果慕斯","status","冷藏中","qty",320,"warehouse","广州中心仓","expireAt","2026-05-13"),
            Map.of("batchNo","CAKE-20260511-002","name","雪顶可可企鹅蛋糕","status","待出库","qty",180,"warehouse","深圳前置仓","expireAt","2026-05-13"),
            Map.of("batchNo","CAKE-20260510-023","name","马卡龙云朵蛋糕","status","临期预警","qty",24,"warehouse","佛山仓","expireAt","2026-05-12")
        ));
    }
    @PostMapping("/damage-reports") public ApiResponse<Map<String,Object>> damage(@RequestBody Map<String,Object> body){
        Map<String,Object> result = new LinkedHashMap<>(body); result.put("reportNo", "DMG" + System.currentTimeMillis()); result.put("status", "REPORTED"); result.put("reportedAt", LocalDateTime.now());
        eventBus.publish("WAREHOUSE_DAMAGE_REPORTED", String.valueOf(result.get("reportNo")), result);
        return ApiResponse.ok("货损已上报", result);
    }
}
