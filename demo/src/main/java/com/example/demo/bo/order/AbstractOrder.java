package com.example.demo.bo.order;

import java.util.UUID;

import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.SymbolCode;

import lombok.Data;

@Data
public abstract class AbstractOrder implements Comparable<AbstractOrder> {
    protected long id;
    protected SymbolCode symbolCode;
    protected OrderDirection orderDirection;
    protected OrderStatus orderStatus;
    protected double excutionPrice; //TODO: Move to interfaces
    protected double quantity;

    protected AbstractOrder(SymbolCode symbolCode, double quantity) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.symbolCode = symbolCode;
        this.quantity = quantity;
        this.orderStatus = OrderStatus.OPEN;
    }

    protected AbstractOrder() {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.orderStatus = OrderStatus.OPEN;
    }

    public abstract double getExecutionPrice();

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    @Override
    public int compareTo(AbstractOrder other) {
        if (this.excutionPrice < other.excutionPrice) {
            return 1;
        } else if (this.excutionPrice > other.excutionPrice) {
            return -1;
        } else {
            //TODO when same price, compare the time stamp
            return Long.compare(this.id, other.id);
        }
    }

    @Override
    public boolean equals(Object obj) {
        //TODO need to compare orderStatus as well
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
        //TODO need to hash orderStatus as well
        return Long.hashCode(id);
    }
}