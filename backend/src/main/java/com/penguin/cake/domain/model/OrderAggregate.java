package com.penguin.cake.domain.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/** 领域聚合：订单统一封装官网订单、外卖平台订单、验券核销订单。 */
public class OrderAggregate {
    private final String orderNo;
    private final String source;
    private String storeCode;
    private String status;
    private BigDecimal amount;
    private final List<String> domainEvents = new ArrayList<>();
    private LocalDateTime updatedAt = LocalDateTime.now();
    public OrderAggregate(String orderNo, String source, BigDecimal amount) { this.orderNo = orderNo; this.source = source; this.amount = amount; this.status = "CREATED"; }
    public void markPaid() { this.status = "PAID"; domainEvents.add("ORDER_PAID"); touch(); }
    public void startBaking() { this.status = "BAKING"; domainEvents.add("ORDER_BAKING_STARTED"); touch(); }
    public void dispatch(String storeCode) { this.storeCode = storeCode; this.status = "DELIVERING"; domainEvents.add("ORDER_DISPATCHED"); touch(); }
    public void complete() { this.status = "COMPLETED"; domainEvents.add("ORDER_COMPLETED"); touch(); }
    private void touch(){ this.updatedAt = LocalDateTime.now(); }
    public String orderNo(){ return orderNo; } public String source(){ return source; } public String storeCode(){ return storeCode; } public String status(){ return status; } public BigDecimal amount(){ return amount; } public List<String> pullEvents(){ var copy = List.copyOf(domainEvents); domainEvents.clear(); return copy; } public LocalDateTime updatedAt(){ return updatedAt; }
}
