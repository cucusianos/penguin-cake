package com.penguin.cake.waimai.dto;
import com.penguin.cake.waimai.WaimaiPlatform;
import java.math.BigDecimal;
public record WaimaiOrderSyncRequest(WaimaiPlatform platform, String platformOrderNo, String storeId, String customerName, String phone, String address, BigDecimal amount, String remark) {}
