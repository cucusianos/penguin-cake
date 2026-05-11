package com.penguin.cake.order;
import com.penguin.cake.common.ApiResponse;
import com.penguin.cake.middleware.CakeEventBus;
import com.penguin.cake.order.dto.OrderTimelineItem;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final CakeEventBus eventBus;
    public OrderController(CakeEventBus eventBus) { this.eventBus = eventBus; }
    @PostMapping
    public ApiResponse<Map<String,Object>> create(@RequestBody Map<String,Object> body) {
        String orderNo = "QG" + System.currentTimeMillis();
        Map<String,Object> order = new LinkedHashMap<>();
        order.put("orderNo", orderNo); order.put("status", OrderStatus.PENDING_PAYMENT); order.put("amount", body.getOrDefault("amount", new BigDecimal("168.00")));
        eventBus.publish("ORDER_CREATED", orderNo, order);
        return ApiResponse.ok("订单已创建", order);
    }
    @GetMapping("/{orderNo}/status")
    public ApiResponse<Map<String,Object>> status(@PathVariable String orderNo) {
        LocalDateTime now = LocalDateTime.now();
        List<OrderTimelineItem> timeline = List.of(
            new OrderTimelineItem(OrderStatus.PENDING_PAYMENT,"待支付","订单已提交，等待顾客支付", now.minusHours(2)),
            new OrderTimelineItem(OrderStatus.PAID,"已支付","支付成功，门店已接单", now.minusMinutes(90)),
            new OrderTimelineItem(OrderStatus.BAKING,"制作中","后厨正在制作蛋糕", now.minusMinutes(55)),
            new OrderTimelineItem(OrderStatus.QUALITY_CHECK,"质检中","冷藏、外观和祝福语检查", now.minusMinutes(20)),
            new OrderTimelineItem(OrderStatus.DELIVERING,"配送中","骑手已取货，正在配送", now.minusMinutes(5))
        );
        return ApiResponse.ok(Map.of("orderNo", orderNo, "currentStatus", OrderStatus.DELIVERING, "storeName", "企鹅蛋糕·广州天河店", "delivery", Map.of("rider","小陈","phone","13800000000","temperature","4°C"), "timeline", timeline));
    }
    @GetMapping("/recent")
    public ApiResponse<List<Map<String,Object>>> recent(){
        return ApiResponse.ok(List.of(
            Map.of("orderNo","QG202605110001","status","DELIVERING","amount",238,"city","广州","channel","官网"),
            Map.of("orderNo","JDWM20260511018","status","BAKING","amount",168,"city","深圳","channel","京东外卖"),
            Map.of("orderNo","TBSG20260511022","status","READY_TO_SHIP","amount",198,"city","佛山","channel","淘宝闪购")
        ));
    }
}
