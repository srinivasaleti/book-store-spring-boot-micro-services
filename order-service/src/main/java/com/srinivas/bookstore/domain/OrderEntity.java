package com.srinivas.bookstore.domain;

import com.srinivas.bookstore.domain.models.Address;
import com.srinivas.bookstore.domain.models.Customer;
import com.srinivas.bookstore.domain.models.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private String username;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "name", column = @Column(name = "customer_name", nullable = false)), @AttributeOverride(name = "email", column = @Column(name = "customer_email", nullable = false)), @AttributeOverride(name = "phone", column = @Column(name = "customer_phone", nullable = false))})
    private Customer customer;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "line1", column = @Column(name = "delivery_address_line1", nullable = false)), @AttributeOverride(name = "line2", column = @Column(name = "delivery_address_line2")), @AttributeOverride(name = "city", column = @Column(name = "delivery_address_city", nullable = false)), @AttributeOverride(name = "state", column = @Column(name = "delivery_address_state", nullable = false)), @AttributeOverride(name = "zipCode", column = @Column(name = "delivery_address_zip_code", nullable = false)), @AttributeOverride(name = "country", column = @Column(name = "delivery_address_country", nullable = false))})
    private Address deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String comments;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setCustomer(@Valid Customer customer) {
        this.customer = customer;
    }

    public void setDeliveryAddress(@Valid Address address) {
        this.deliveryAddress = address;
    }

    public void setOrderItems(@NotEmpty(message = "Items can not be empty") List<OrderItemEntity> orderItems) {
        this.items = orderItems;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
