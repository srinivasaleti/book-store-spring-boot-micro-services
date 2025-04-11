package com.srinivas.bookstore.domain.models;

import com.srinivas.bookstore.domain.OrderItemEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateOrderRequest(@NotEmpty(message = "Items can not be empty") @Valid List<OrderItem> orderItems,
                                 @Valid Customer customer, @Valid Address deliveryAddress) {
}
