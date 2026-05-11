package com.penguin.cake.infrastructure.geo;
import com.penguin.cake.common.ApiResponse;
import com.penguin.cake.middleware.CakeEventBus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/geo")
public class GeoReportController {
    private final CakeEventBus bus;
    public GeoReportController(CakeEventBus bus){ this.bus = bus; }
    @PostMapping("/location-reports") public ApiResponse<Map<String,Object>> report(@RequestBody GeoLocationReport report){
        GeoLocationReport enriched = new GeoLocationReport(report.deliveryNo(), report.riderId(), report.lng(), report.lat(), report.temperature(), report.status(), LocalDateTime.now());
        bus.publish("DELIVERY_GEO_REPORTED", report.deliveryNo(), enriched);
        return ApiResponse.ok(Map.of("accepted", true, "mapProvider", "AMAP", "report", enriched));
    }
}
