package com.penguin.cake.domain.repository;
import com.penguin.cake.domain.model.OrderAggregate;
import java.util.Optional;
public interface OrderRepositoryPort { Optional<OrderAggregate> find(String orderNo); void save(OrderAggregate aggregate); }
