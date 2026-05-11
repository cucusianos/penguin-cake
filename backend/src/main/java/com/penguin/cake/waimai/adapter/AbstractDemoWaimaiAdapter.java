package com.penguin.cake.waimai.adapter;
import com.penguin.cake.waimai.dto.*;
import java.time.LocalDateTime;
import java.util.UUID;
public abstract class AbstractDemoWaimaiAdapter implements WaimaiAdapter {
    public WaimaiSyncResponse receiveOrder(WaimaiOrderSyncRequest request) {
        return new WaimaiSyncResponse(platform(), request.platformOrderNo(), "QG" + UUID.randomUUID().toString().substring(0,8).toUpperCase(), "ACCEPTED", "歪买订单已同步到企鹅蛋糕", LocalDateTime.now());
    }
    public WaimaiSyncResponse pushStatus(WaimaiOrderStatusRequest request) {
        return new WaimaiSyncResponse(platform(), request.platformOrderNo(), "-", request.status(), "状态已回传到歪买平台Mock通道", LocalDateTime.now());
    }
}
