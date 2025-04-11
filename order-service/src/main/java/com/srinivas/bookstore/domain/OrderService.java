package com.srinivas.bookstore.domain;

import com.srinivas.bookstore.domain.models.CreateOrderRequest;
import com.srinivas.bookstore.domain.models.CreateOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public CreateOrderResponse createOrder(String userName, CreateOrderRequest request) {
        OrderEntity order = OrderMapper.toOrder(request);
        order.setUserName(userName);
        OrderEntity savedOrder = orderRepository.save(order);
        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }
}
