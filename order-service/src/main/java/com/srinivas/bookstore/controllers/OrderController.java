package com.srinivas.bookstore.controllers;

import com.srinivas.bookstore.domain.OrderService;
import com.srinivas.bookstore.domain.OrderSummaryDTO;
import com.srinivas.bookstore.domain.SecurityService;
import com.srinivas.bookstore.domain.models.CreateOrderRequest;
import com.srinivas.bookstore.domain.models.CreateOrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final SecurityService securityService;

    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest orderRequest) {
        String userName = securityService.getLoginUserName();
        return orderService.createOrder(userName, orderRequest);
    }

    @GetMapping
    public ResponseEntity<List<OrderSummaryDTO>> getAllOrders() {
        List<OrderSummaryDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
