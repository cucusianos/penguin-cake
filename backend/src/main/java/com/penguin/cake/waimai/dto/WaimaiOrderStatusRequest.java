package com.penguin.cake.waimai.dto;
import com.penguin.cake.waimai.WaimaiPlatform;
public record WaimaiOrderStatusRequest(WaimaiPlatform platform, String platformOrderNo, String status, String riderName, String riderPhone) {}
