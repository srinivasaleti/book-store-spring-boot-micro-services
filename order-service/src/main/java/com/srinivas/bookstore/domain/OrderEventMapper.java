package com.srinivas.bookstore.domain;

import com.srinivas.bookstore.domain.models.OrderCreatedEvent;
import com.srinivas.bookstore.domain.models.OrderItem;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderEventMapper {

  public static OrderCreatedEvent buildOrderCreatedEvent(OrderEntity orderEntity) {
    String eventId = UUID.randomUUID().toString();

    Set<OrderItem> items =
        orderEntity.getItems().stream()
            .map(OrderEventMapper::mapToOrderItem)
            .collect(Collectors.toSet());

    return new OrderCreatedEvent(
        eventId,
        orderEntity.getOrderNumber(),
        items,
        orderEntity.getCustomer(),
        orderEntity.getDeliveryAddress(),
        orderEntity.getCreatedAt(),
        LocalDateTime.now());
  }

  private static OrderItem mapToOrderItem(OrderItemEntity entity) {
    OrderItem order = new OrderItem();
    order.setCode(entity.getProductCode());
    order.setPrice(entity.getPrice());
    order.setQuantity(entity.getQuantity());
    order.setName(entity.getProductName());
    return order;
  }
}
