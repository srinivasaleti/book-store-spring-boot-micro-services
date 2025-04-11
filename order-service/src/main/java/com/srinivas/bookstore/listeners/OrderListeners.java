package com.srinivas.bookstore.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderListeners {

    @RabbitListener(queues = "new_orders")
    public void handleNewOrder(String message) {
        System.out.println("New order received: " + message);
    }

    @RabbitListener(queues = "cancelled_orders")
    public void handleCancelledOrder(String message) {
        System.out.println("Order cancelled: " + message);
    }

    @RabbitListener(queues = "error_orders")
    public void handleErrorOrder(String message) {
        System.out.println("Error in order: " + message);
    }

    @RabbitListener(queues = "delivered_orders")
    public void handleDeliveredOrder(String message) {
        System.out.println("Order delivered: " + message);
    }
}
