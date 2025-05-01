package com.srinivas.bookstore.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderRepository extends JpaRepository<OrderEntity, Long> {
  Optional<OrderEntity> findByOrderNumber(String orderNumber);
}
