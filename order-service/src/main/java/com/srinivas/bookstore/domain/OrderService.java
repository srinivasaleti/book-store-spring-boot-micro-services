package com.srinivas.bookstore.domain;

import com.srinivas.bookstore.domain.exceptions.OrderNotFoundException;
import com.srinivas.bookstore.domain.models.CreateOrderRequest;
import com.srinivas.bookstore.domain.models.CreateOrderResponse;
import com.srinivas.bookstore.domain.models.OrderCreatedEvent;
import com.srinivas.bookstore.domain.validators.OrderValidator;
import jakarta.transaction.Transactional;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderValidator orderValidator;
    private final OrderEventService orderEventService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderValidator orderValidator, OrderEventService orderEventService) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.orderEventService = orderEventService;
    }

    public CreateOrderResponse createOrder(String userName, CreateOrderRequest request) {
        logger.info("Validating order");
        orderValidator.validate(request);

        OrderEntity order = OrderMapper.toOrder(request);
        order.setUserName(userName);

        logger.info("Creating order {}", StructuredArguments.keyValue("orderNumber", order.getOrderNumber()));

        OrderEntity savedOrder = orderRepository.save(order);
        logger.info("Order created successfully {} {}", StructuredArguments.keyValue("orderID", savedOrder.getOrderID()), StructuredArguments.keyValue("orderNumber", savedOrder.getOrderNumber()));
        OrderCreatedEvent orderCreatedEvent = OrderEventMapper.buildOrderCreatedEvent(savedOrder);
        orderEventService.saveOrderCreatedEvent(orderCreatedEvent);

        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }

    public List<OrderSummaryDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderSummaryDTO(
                        order.getOrderNumber(),
                        order.getCustomer().name(),
                        order.getStatus(),
                        order.getCreatedAt(),
                        order.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }
    
    public OrderEntity getOrderByOrderNumber(String orderNumber) {
        logger.info("Fetching order with order number {}", StructuredArguments.keyValue("orderNumber", orderNumber));
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with order number: " + orderNumber));
    }
}
