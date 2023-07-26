package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;
import com.example.demo.model.UserOrder;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    UserOrder findByUser(User user);
}