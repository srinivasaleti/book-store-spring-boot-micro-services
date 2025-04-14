package com.srinivas.bookstore.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srinivas.bookstore.config.ApplicationConfig;
import com.srinivas.bookstore.domain.models.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationConfig applicationConfig;
    private final  ObjectMapper objectMapper;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate, ApplicationConfig applicationConfig, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationConfig = applicationConfig;
        this.objectMapper = objectMapper;
    }

    public void publish(OrderCreatedEvent orderCreatedEvent) {
        this.send(this.applicationConfig.routingNew, orderCreatedEvent);
    }

    private void send(String rotingKey, Object payload) {
        this.rabbitTemplate.convertAndSend(this.applicationConfig.exchange, rotingKey, formatToString(payload));
    }

    private Object formatToString(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
