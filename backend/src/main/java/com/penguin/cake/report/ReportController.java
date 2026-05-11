package com.penguin.cake.report;
import com.penguin.cake.common.ApiResponse;
import com.penguin.cake.application.dashboard.PearlRiverDeltaDashboardService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final PearlRiverDeltaDashboardService dashboardService;
    public ReportController(PearlRiverDeltaDashboardService dashboardService){ this.dashboardService = dashboardService; }
    @GetMapping("/overview") public ApiResponse<Map<String,Object>> overview(){
        return ApiResponse.ok(Map.of(
            "todaySales", new BigDecimal("1268000.00"), "todayOrders", 4927, "stores", 12,
            "delivering", 386, "damageRate", "0.72%", "warehouseHealth", "97.4%",
            "channels", List.of("官网", "京东外卖", "淘宝闪购", "美团券", "口碑券", "抖音券")
        ));
    }
    @GetMapping("/pearl-river-delta") public ApiResponse<List<Map<String,Object>>> prd(){ return ApiResponse.ok(dashboardService.stores()); }
}
