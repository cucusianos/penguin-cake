package com.penguin.cake.procurement;
import com.penguin.cake.common.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/procurement")
public class ProcurementController {
    @GetMapping("/materials") public ApiResponse<List<Map<String,Object>>> materials(){
        return ApiResponse.ok(List.of(
            Map.of("sku","RAW-CREAM","name","动物淡奶油","stock",860,"unit","L","supplier","湾区乳业","quality","合格","warning",false),
            Map.of("sku","RAW-FLOUR","name","低筋面粉","stock",1280,"unit","kg","supplier","南粤粮油","quality","合格","warning",false),
            Map.of("sku","RAW-STRAWBERRY","name","草莓","stock",96,"unit","kg","supplier","从化果园","quality","需复检","warning",true),
            Map.of("sku","RAW-CHOCOLATE","name","比利时黑巧","stock",168,"unit","kg","supplier","进口烘焙","quality","合格","warning",false)
        ));
    }
    @GetMapping("/purchase-orders") public ApiResponse<List<Map<String,Object>>> orders(){
        return ApiResponse.ok(List.of(
            Map.of("poNo","PO20260511001","material","动物淡奶油","qty",300,"amount",13200,"status","已入库"),
            Map.of("poNo","PO20260511002","material","草莓","qty",120,"amount",4560,"status","质检中"),
            Map.of("poNo","PO20260511003","material","海盐芝士乳酪","qty",5000,"amount",9800,"status","运输中")
        ));
    }
}
