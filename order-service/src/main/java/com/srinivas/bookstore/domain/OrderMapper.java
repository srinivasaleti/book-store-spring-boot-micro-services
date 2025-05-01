package com.srinivas.bookstore.domain;

import com.srinivas.bookstore.domain.models.CreateOrderRequest;
import com.srinivas.bookstore.domain.models.OrderItem;
import com.srinivas.bookstore.domain.models.OrderStatus;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

  public static OrderEntity toOrder(CreateOrderRequest request) {
    OrderEntity newOrder = new OrderEntity();

    newOrder.setCustomer(request.customer());
    newOrder.setOrderNumber(UUID.randomUUID().toString());
    newOrder.setDeliveryAddress(request.deliveryAddress());
    newOrder.setStatus(OrderStatus.NEW);
    newOrder.setDeliveryAddress(request.deliveryAddress());

    List<OrderItemEntity> orderItemEntities = new ArrayList<>();
    for (OrderItem item : request.orderItems()) {
      OrderItemEntity newItem = new OrderItemEntity();
      newItem.setCode(item.getCode());
      newItem.setName(item.getName());
      newItem.setQuantity(item.getQuantity());
      newItem.setPrice(item.getPrice());
      newItem.setOrder(newOrder);
      orderItemEntities.add(newItem);
    }

    newOrder.setOrderItems(orderItemEntities);
    return newOrder;
  }
}
