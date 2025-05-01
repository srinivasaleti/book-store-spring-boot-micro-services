package com.srinivas.bookstore.domain;

import com.srinivas.bookstore.domain.models.OrderEventType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_events")
public class OrderEventEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_event_id_seq")
  @SequenceGenerator(
      name = "order_event_id_seq",
      sequenceName = "order_event_id_seq",
      allocationSize = 50)
  private Long id;

  @Column(name = "order_number", nullable = false)
  private String orderNumber;

  @Column(name = "event_id", nullable = false, unique = true)
  private String eventId;

  @Column(name = "event_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderEventType eventType;

  @Column(name = "payload", nullable = false, columnDefinition = "TEXT")
  private String payload;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public OrderEventType getEventType() {
    return eventType;
  }

  public void setEventType(OrderEventType eventType) {
    this.eventType = eventType;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
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
