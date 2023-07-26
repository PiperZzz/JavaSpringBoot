package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bo.Order;
import com.example.demo.model.UserOrder;

public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    Order findByOrderId(Long orderId);
}