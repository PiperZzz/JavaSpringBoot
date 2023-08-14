package com.example.demo.bo.order;

import com.example.demo.enums.OrderType;
import com.example.demo.enums.Symbol;

import lombok.Data;

@Data
public abstract class AbstractOrder implements Comparable<AbstractOrder> {
    private long id;
    private Symbol symbol;
    private OrderType type; //TODO interface
    private double price;
    private double amount;

    //TODO abastrct buy and sell direction 

    protected AbstractOrder(long id, Symbol symbol, OrderType type, double price, double amount) {
        this.id = id;
        this.symbol = symbol;
        this.type = type;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public int compareTo(AbstractOrder other) {
        if (this.price < other.price) {
            return 1;
        } else if (this.price > other.price) {
            return -1;
        } else {
            return 0;
        }
    }
}