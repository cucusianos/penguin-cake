package com.penguin.cake.takeout.adapter;
import com.penguin.cake.takeout.TakeoutPlatform;
import com.penguin.cake.takeout.dto.*;
public interface TakeoutAdapter {
    TakeoutPlatform platform();
    TakeoutSyncResponse receiveOrder(TakeoutOrderSyncRequest request);
    TakeoutSyncResponse pushStatus(TakeoutOrderStatusRequest request);
}
