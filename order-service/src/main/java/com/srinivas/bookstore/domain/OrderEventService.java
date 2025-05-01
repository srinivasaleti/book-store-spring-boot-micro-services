package com.srinivas.bookstore.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srinivas.bookstore.domain.models.OrderCreatedEvent;
import com.srinivas.bookstore.domain.models.OrderEventType;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderEventService {

  private static final Logger logger = LoggerFactory.getLogger(OrderEventService.class);

  private final OrderEventRepository orderEventRepository;
  private final OrderEventPublisher orderEventPublisher;
  private final ObjectMapper objectMapper;

  public OrderEventService(
      OrderEventRepository orderEventRepository,
      ObjectMapper objectMapper,
      OrderEventPublisher orderEventPublisher) {
    this.orderEventRepository = orderEventRepository;
    this.objectMapper = objectMapper;
    this.orderEventPublisher = orderEventPublisher;
  }

  public void saveOrderCreatedEvent(OrderCreatedEvent event) {
    OrderEventEntity orderEventEntity = new OrderEventEntity();
    orderEventEntity.setEventId(event.eventId());
    orderEventEntity.setOrderNumber(event.orderNumber());
    orderEventEntity.setEventType(OrderEventType.ORDER_CREATED);
    orderEventEntity.setCreatedAt(event.createdAt());
    orderEventEntity.setPayload(toJsonPayload(event));
    orderEventEntity.setUpdatedAt(LocalDateTime.now());
    orderEventRepository.save(orderEventEntity);
    logger.info("Order event saved successfully for orderNumber: {}", event.orderNumber());
  }

  public void publishOrderEvents() {
    Sort sort = Sort.by("createdAt").ascending();
    List<OrderEventEntity> orderEventEntities = orderEventRepository.findAll();

    for (OrderEventEntity orderEventEntity : orderEventEntities) {
      this.publishEvents(orderEventEntity);
      orderEventRepository.delete(orderEventEntity);
    }
  }

  private void publishEvents(OrderEventEntity orderEventEntity) {
    OrderEventType orderEventType = orderEventEntity.getEventType();

    switch (orderEventType) {
      case ORDER_CREATED:
        OrderCreatedEvent orderCreatedEvent = fromJSONPayload(orderEventEntity.getPayload());
        orderEventPublisher.publish(orderCreatedEvent);
        break;
      default:
        logger.warn("Unsupported OrderEventType: {}", orderEventType);
    }
  }

  public String toJsonPayload(OrderCreatedEvent event) {
    try {
      return this.objectMapper.writeValueAsString(event);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private OrderCreatedEvent fromJSONPayload(String payload) {
    try {
      return this.objectMapper.readValue(payload, OrderCreatedEvent.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
