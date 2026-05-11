package com.penguin.cake.middleware;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 蛋糕解耦中间件：统一事件信封、幂等键、路由、审计与本地 Outbox。
 * 本 Demo 用内存实现；生产环境建议替换为 RabbitMQ/Kafka + MySQL Outbox。
 */
@Component
public class CakeDecouplingMiddleware {
    private final List<EventEnvelope> outbox = Collections.synchronizedList(new ArrayList<>());
    public EventEnvelope wrap(String topic, String aggregateId, Object payload) {
        String id = "EVT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String idempotentKey = topic + ":" + aggregateId;
        return new EventEnvelope(id, topic, aggregateId, idempotentKey, Map.of("producer", "penguin-cake"), payload, LocalDateTime.now());
    }
    public EventEnvelope publish(String topic, String aggregateId, Object payload) { EventEnvelope envelope = wrap(topic, aggregateId, payload); outbox.add(0, envelope); return envelope; }
    public List<EventEnvelope> recent(int limit) { return outbox.stream().limit(limit).toList(); }
}
