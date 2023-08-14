package com.example.demo.controller.dto;

import lombok.Data;
@Data
public class TradeRequest {
    private String orderId;
    private String targetOrderId;
    private String username;
    private String symbol;
    private String symbolPair;
    private String type;
    private String direction;
    private double lmitePrice;
    private double stopPrice;
    private double quantity;
}