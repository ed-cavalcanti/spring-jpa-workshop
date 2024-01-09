package com.testspring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testspring.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
