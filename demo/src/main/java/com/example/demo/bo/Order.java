package com.example.demo.bo;

import com.example.demo.enums.OrderType;
import com.example.demo.enums.Symbol;

import lombok.Data;

@Data
public class Order implements Comparable<Order> {
    private String id;
    private Symbol symbol;
    private OrderType type;
    private double price;
    private double amount;

    public Order(String id, Symbol symbol, OrderType type, double price, double amount) {
        this.id = id;
        this.symbol = symbol;
        this.type = type;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public int compareTo(Order other) {
        if (this.price < other.price) {
            return 1;
        } else if (this.price > other.price) {
            return -1;
        } else {
            return 0;
        }
    }
}