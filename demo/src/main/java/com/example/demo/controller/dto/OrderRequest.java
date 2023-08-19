package com.example.demo.controller.dto;

import lombok.Data;
@Data
public class OrderRequest {
    private String id;
    private String targetId;
    private String username;
    private String token;
    private String symbol;
    private String symbolPair;
    private String orderType;
    private String orderDirection;
    private double excutionPrice;
    private double stopPrice;
    private double limitPrice;
    private double quantity;
    private long expirationTime;
    private boolean isMarketOrder;
    private boolean isStopOrder;
}