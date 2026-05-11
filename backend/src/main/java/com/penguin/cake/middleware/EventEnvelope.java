package com.penguin.cake.middleware;
import java.time.LocalDateTime;
import java.util.Map;
public record EventEnvelope(String eventId, String topic, String aggregateId, String idempotentKey, Map<String,Object> headers, Object payload, LocalDateTime createdAt) {}
