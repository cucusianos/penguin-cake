package com.penguin.cake.order.dto;
import com.penguin.cake.order.OrderStatus;
import java.time.LocalDateTime;
public record OrderTimelineItem(OrderStatus status, String title, String description, LocalDateTime time) {}
