package com.penguin.cake.middleware;
import java.time.LocalDateTime;
public record IntegrationEvent(String eventId, String eventType, String bizNo, Object payload, LocalDateTime createdAt) {}
