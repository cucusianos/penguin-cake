package com.penguin.cake.takeout.dto;
import com.penguin.cake.takeout.TakeoutPlatform;
import java.time.LocalDateTime;
public record TakeoutSyncResponse(TakeoutPlatform platform, String platformOrderNo, String penguinOrderNo, String status, String message, LocalDateTime syncedAt) {}
