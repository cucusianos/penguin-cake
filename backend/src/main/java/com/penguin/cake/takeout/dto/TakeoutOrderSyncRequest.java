package com.penguin.cake.takeout.dto;
import com.penguin.cake.takeout.TakeoutPlatform;
import java.math.BigDecimal;
public record TakeoutOrderSyncRequest(TakeoutPlatform platform, String platformOrderNo, String storeId, String customerName, String phone, String address, BigDecimal amount, String remark) {}
