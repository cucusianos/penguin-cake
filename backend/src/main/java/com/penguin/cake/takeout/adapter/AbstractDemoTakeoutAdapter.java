package com.penguin.cake.takeout.adapter;
import com.penguin.cake.takeout.dto.*;
import java.time.LocalDateTime;
import java.util.UUID;
public abstract class AbstractDemoTakeoutAdapter implements TakeoutAdapter {
    public TakeoutSyncResponse receiveOrder(TakeoutOrderSyncRequest request) {
        return new TakeoutSyncResponse(platform(), request.platformOrderNo(), "QG" + UUID.randomUUID().toString().substring(0,8).toUpperCase(), "ACCEPTED", "外卖订单已同步到企鹅蛋糕", LocalDateTime.now());
    }
    public TakeoutSyncResponse pushStatus(TakeoutOrderStatusRequest request) {
        return new TakeoutSyncResponse(platform(), request.platformOrderNo(), "-", request.status(), "状态已回传到外卖平台Mock通道", LocalDateTime.now());
    }
}
