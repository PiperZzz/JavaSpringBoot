package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private String orderType; // BUY or SELL
    private String orderStatus; // OPEN, COMPLETED, CANCELLED
    private String symbol;
    private Double amount;
    private Double price;
    private Double fee;
    private Double total;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
}