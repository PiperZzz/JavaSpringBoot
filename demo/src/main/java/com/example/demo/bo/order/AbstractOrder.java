package com.example.demo.bo.order;

import com.example.demo.enums.OrderType;
import com.example.demo.enums.Symbol;

import lombok.Data;

@Data
public abstract class AbstractOrder implements Comparable<AbstractOrder> {
    private long id;
    private Symbol symbol;
    protected OrderType orderDirection; //TODO interface
    private double price;
    private double quantity;

    //TODO abastrct buy and sell direction 

    protected AbstractOrder(long id, Symbol symbol, double price, double quantity) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderType getOrderDirection() {
        return orderDirection;
    }

    @Override
    public int compareTo(AbstractOrder other) {
        if (this.price < other.price) {
            return 1;
        } else if (this.price > other.price) {
            return -1;
        } else {
            return Long.compare(this.id, other.id); //TODO compare the time stamp
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractOrder other = (AbstractOrder) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}