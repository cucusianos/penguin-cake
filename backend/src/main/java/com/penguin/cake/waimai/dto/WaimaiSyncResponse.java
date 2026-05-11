package com.penguin.cake.waimai.dto;
import com.penguin.cake.waimai.WaimaiPlatform;
import java.time.LocalDateTime;
public record WaimaiSyncResponse(WaimaiPlatform platform, String platformOrderNo, String penguinOrderNo, String status, String message, LocalDateTime syncedAt) {}
