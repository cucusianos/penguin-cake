package com.penguin.cake.domain.event;
import java.time.LocalDateTime;
import java.util.Map;
public record CakeDomainEvent(String eventId, String eventType, String aggregateId, String idempotentKey, Map<String,Object> payload, LocalDateTime occurredAt) {}
