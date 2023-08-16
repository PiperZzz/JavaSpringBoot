package com.example.demo.bo.order;

import java.util.UUID;
import java.time.LocalDateTime;

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
    protected double quantity;
    protected double excutionPrice;
    protected LocalDateTime orderOpenAt;
    protected LocalDateTime orderCloseAt;
    protected LocalDateTime orderUpdateAt;

    //TODO remove unused constructors
    protected AbstractOrder(SymbolCode symbolCode, double quantity, double excutionPrice) {
        id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        orderStatus = OrderStatus.OPEN;
        orderOpenAt = LocalDateTime.now();
        this.symbolCode = symbolCode;
        this.quantity = quantity;
        this.excutionPrice = excutionPrice;
    }

    protected AbstractOrder(SymbolCode symbolCode, double quantity) {
        id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        orderStatus = OrderStatus.OPEN;
        orderOpenAt = LocalDateTime.now();
        this.quantity = quantity;
        this.symbolCode = symbolCode;
    }

    protected AbstractOrder() {
        id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        orderStatus = OrderStatus.OPEN;
        orderOpenAt = LocalDateTime.now();
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