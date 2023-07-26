package com.example.demo.bo;

import com.example.demo.enums.OrderType;

import lombok.Data;

@Data
public class Order implements Comparable<Order> {
    private String id;
    private OrderType orderType;
    private double price;
    private double amount;

    public Order(String id, OrderType orderType, double price, double amount) {
        this.id = id;
        this.orderType = orderType;
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