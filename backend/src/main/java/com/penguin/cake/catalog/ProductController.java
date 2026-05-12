package com.penguin.cake.catalog;

import com.penguin.cake.common.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @GetMapping
    public ApiResponse<List<Map<String, Object>>> list(@RequestParam(required=false) String keyword) {
        List<Map<String, Object>> all = List.of(
            product(1,"企鹅莓果慕斯","草莓、蓝莓与轻芝士慕斯，珠三角冷链当日达", new BigDecimal("168.00"),"/images/cake-strawberry.png"),
            product(2,"雪顶可可企鹅蛋糕","比利时黑巧、淡奶油、榛果碎", new BigDecimal("198.00"),"/images/cake-hazelnut.png"),
            product(3,"巴斯克焦香芝士蛋糕","焦香芝士、海盐奶油、绵密乳酪胚", new BigDecimal("238.00"),"/images/cake-hero.png"),
            product(4,"黑森林莓果蛋糕","黑巧戚风、樱桃酒香、莓果夹心，支持祝福语定制", new BigDecimal("258.00"),"/images/cake-realistic-1.png")
        );
        if (keyword == null || keyword.isBlank()) return ApiResponse.ok(all);
        return ApiResponse.ok(all.stream().filter(p -> String.valueOf(p.get("name")).contains(keyword)).toList());
    }
    @GetMapping("/{id}")
    public ApiResponse<Map<String,Object>> detail(@PathVariable Integer id) {
        return ApiResponse.ok(product(id,"企鹅莓果慕斯","草莓、蓝莓、马斯卡彭、低糖蛋糕胚，可选尺寸/口味/祝福语", new BigDecimal("168.00"),"/images/cake-hero.png"));
    }
    private Map<String,Object> product(Integer id,String name,String desc,BigDecimal price,String image){
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("id",id); map.put("name",name); map.put("description",desc); map.put("price",price); map.put("image",image);
        map.put("tags", List.of("真实摄影","冷链配送","支持定制"));
        return map;
    }
}
