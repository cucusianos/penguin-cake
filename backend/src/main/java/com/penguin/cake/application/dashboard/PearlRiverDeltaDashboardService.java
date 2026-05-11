package com.penguin.cake.application.dashboard;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PearlRiverDeltaDashboardService {
    public List<Map<String,Object>> stores(){
        return List.of(
            city("广州",113.2644,23.1291,18.6,1286), city("深圳",114.0579,22.5431,21.8,1520), city("珠海",113.5767,22.2707,8.9,486),
            city("佛山",113.1214,23.0215,9.8,722), city("东莞",113.7518,23.0205,11.3,801), city("中山",113.3926,22.5176,7.6,390),
            city("江门",113.0819,22.5787,5.1,277), city("惠州",114.4126,23.0794,6.8,368), city("肇庆",112.4651,23.0472,4.6,246),
            city("清远",113.0560,23.6818,3.9,198), city("香港",114.1694,22.3193,12.6,615), city("澳门",113.5439,22.1987,4.2,188)
        );
    }
    private Map<String,Object> city(String name,double lng,double lat,double sales,int orders){ return Map.of("name", name, "lng", lng, "lat", lat, "salesAmountWan", BigDecimal.valueOf(sales), "orders", orders); }
}
