package com.example.demo.bo;

import java.time.LocalDateTime;

import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.OrderType;
import com.example.demo.enums.Symbol;

import lombok.Data;

@Data
public class OrderEvent {
    private long orderId;
    private long userId;
    private Symbol symbol;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private double amount;
    private double price;
    private double fee;
    private double total;
    private LocalDateTime orderOpenTime;
    private LocalDateTime orderCloseTime;
    private LocalDateTime orderUpdateTime;
}
