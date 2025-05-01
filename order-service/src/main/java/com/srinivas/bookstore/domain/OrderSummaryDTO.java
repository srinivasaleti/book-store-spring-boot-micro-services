package com.srinivas.bookstore.domain;

import com.srinivas.bookstore.domain.models.OrderStatus;
import java.time.LocalDateTime;

public class OrderSummaryDTO {
  private String orderNumber;
  private String customerName;
  private OrderStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  // Constructors
  public OrderSummaryDTO() {}

  public OrderSummaryDTO(
      String orderNumber,
      String customerName,
      OrderStatus status,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.orderNumber = orderNumber;
    this.customerName = customerName;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  // Getters and Setters
  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
