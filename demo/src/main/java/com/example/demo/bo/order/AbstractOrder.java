package com.example.demo.bo.order;

import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.SymbolCode;

import lombok.Data;

@Data
public abstract class AbstractOrder implements Comparable<AbstractOrder> {
    private long id;
    private SymbolCode symbol;
    protected OrderDirection orderDirection; //TODO interface
    private double price;
    private double quantity;

    //TODO abastrct buy and sell direction 

    protected AbstractOrder(long id, SymbolCode symbol, double price, double quantity) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    protected AbstractOrder(long id, SymbolCode symbol, double quantity) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
    }

    protected AbstractOrder() {

    }

    public abstract double setExecutionPrice();

    public OrderDirection getOrderDirection() {
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