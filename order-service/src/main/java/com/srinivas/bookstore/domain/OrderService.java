package com.srinivas.bookstore.domain;

import com.srinivas.bookstore.domain.models.CreateOrderRequest;
import com.srinivas.bookstore.domain.models.CreateOrderResponse;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public CreateOrderResponse createOrder(String userName, CreateOrderRequest request) {
        OrderEntity order = OrderMapper.toOrder(request);
        order.setUserName(userName);

        logger.info("Creating order {}",
                StructuredArguments.keyValue("orderNumber", order.getOrderNumber())
        );

        OrderEntity savedOrder = orderRepository.save(order);

        logger.info("Order created successfully {} {}",
                StructuredArguments.keyValue("orderID", savedOrder.getOrderID()),
                StructuredArguments.keyValue("orderNumber", savedOrder.getOrderNumber())
        );

        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }
}
