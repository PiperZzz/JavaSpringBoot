package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.OrderType;
import com.example.demo.enums.Symbol;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private Symbol symbol;
    
    private Double amount;
    private Double price;
    private Double fee;
    private Double total;
    
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
}