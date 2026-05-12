package com.penguin.cake.takeout.dto;
import com.penguin.cake.takeout.TakeoutPlatform;
public record TakeoutOrderStatusRequest(TakeoutPlatform platform, String platformOrderNo, String status, String riderName, String riderPhone) {}
