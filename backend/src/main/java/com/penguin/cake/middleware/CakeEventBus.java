package com.penguin.cake.middleware;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class CakeEventBus {
    private final CakeDecouplingMiddleware middleware;
    public CakeEventBus(CakeDecouplingMiddleware middleware) { this.middleware = middleware; }
    public void publish(String eventType, String aggregateId, Object payload) { middleware.publish(eventType, aggregateId, payload); }
    public List<EventEnvelope> recentEvents(){ return middleware.recent(30); }
}
