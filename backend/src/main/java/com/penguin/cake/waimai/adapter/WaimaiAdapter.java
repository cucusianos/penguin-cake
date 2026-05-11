package com.penguin.cake.waimai.adapter;
import com.penguin.cake.waimai.WaimaiPlatform;
import com.penguin.cake.waimai.dto.*;
public interface WaimaiAdapter {
    WaimaiPlatform platform();
    WaimaiSyncResponse receiveOrder(WaimaiOrderSyncRequest request);
    WaimaiSyncResponse pushStatus(WaimaiOrderStatusRequest request);
}
