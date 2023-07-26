package com.example.demo.controller.dto;

import lombok.Data;

@Data
public class TradeRequest {
    private String username;
    private String symbol;
    private String type;
    private double price;
    private double amount;
}