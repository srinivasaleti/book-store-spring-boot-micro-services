package com.srinivas.bookstore.controllers;

import com.srinivas.bookstore.config.ApplicationConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationConfig applicationConfig;

    public OrderController(RabbitTemplate rabbitTemplate, ApplicationConfig applicationConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationConfig = applicationConfig;
    }

    @PostMapping("/new")
    public ResponseEntity<String> sendNewOrder(@RequestBody String orderJson) {
        rabbitTemplate.convertAndSend(this.applicationConfig.exchange, this.applicationConfig.routingNew, orderJson);
        return ResponseEntity.ok("Message sent to new_orders queue!");
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> sendCancelledOrder(@RequestBody String orderJson) {
        rabbitTemplate.convertAndSend(this.applicationConfig.exchange, this.applicationConfig.routingCancel, orderJson);
        return ResponseEntity.ok("Message sent to canceled_orders queue!");
    }
}
