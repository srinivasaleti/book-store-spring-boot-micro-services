package com.srinivas.bookstore.controllers;

import com.srinivas.bookstore.config.ApplicationConfig;
import com.srinivas.bookstore.domain.OrderService;
import com.srinivas.bookstore.domain.SecurityService;
import com.srinivas.bookstore.domain.models.CreateOrderRequest;
import com.srinivas.bookstore.domain.models.CreateOrderResponse;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationConfig applicationConfig;
    private final OrderService orderService;
    private final SecurityService securityService;

    public OrderController(RabbitTemplate rabbitTemplate, ApplicationConfig applicationConfig, OrderService orderService, SecurityService securityService) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationConfig = applicationConfig;
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest orderRequest) {
        String userName = securityService.getLoginUserName();
        return orderService.createOrder(userName, orderRequest);
    }
}
