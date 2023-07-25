package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.enums.Symbol;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Enumerated(EnumType.STRING)
    private Symbol symbol;
    
    private Double amount;
    private Double price;
    private Double fee;
    private Double total;
    
    private LocalDateTime tradeTime;
}