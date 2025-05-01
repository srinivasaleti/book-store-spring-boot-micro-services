package com.srinivas.bookstore.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {}
