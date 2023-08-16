package com.example.demo.bo.order;

import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.SymbolCode;

import lombok.Data;

@Data
public abstract class AbstractOrder implements Comparable<AbstractOrder> {
    protected long id;
    protected SymbolCode symbol;
    protected OrderDirection orderDirection;
    protected OrderStatus orderStatus;
    protected double price;
    protected double quantity;

    protected AbstractOrder() {
        //TODO abastrct buy and sell direction
    }

    public abstract double getExecutionPrice();

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
            //TODO when same price, compare the time stamp
            return Long.compare(this.id, other.id);
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