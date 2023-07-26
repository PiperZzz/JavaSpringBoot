package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TradingOrder;

public interface OrderRepository extends JpaRepository<TradingOrder, Long> {
    
}