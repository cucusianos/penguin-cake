package com.penguin.cake.infrastructure.geo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public record GeoLocationReport(String deliveryNo, String riderId, BigDecimal lng, BigDecimal lat, BigDecimal temperature, String status, LocalDateTime reportedAt) {}
