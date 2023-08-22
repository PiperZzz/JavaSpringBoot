package com.example.demo.bo.order;

import java.util.UUID;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.SymbolCode;

import lombok.Data;

@Data
public abstract class AbstractOrder implements Comparable<AbstractOrder>, Serializable {
    protected long id;
    protected SymbolCode symbolCode;
    protected OrderDirection orderDirection;
    protected OrderStatus orderStatus;
    protected double quantity;
    protected double excutionPrice;
    protected long expirationTime;
    protected LocalDateTime orderOpenAt;
    protected LocalDateTime orderCloseAt;
    protected LocalDateTime orderUpdateAt;
    protected LocalDateTime orderExpirationAt;

    //TODO remove unused constructors
    protected AbstractOrder(SymbolCode symbolCode, double quantity, double excutionPrice) {
        id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        orderStatus = OrderStatus.OPEN;
        orderOpenAt = LocalDateTime.now();
        orderExpirationAt = orderOpenAt.plus(expirationTime, ChronoUnit.MILLIS);
        this.symbolCode = symbolCode;
        this.quantity = quantity;
        this.excutionPrice = excutionPrice;
    }

    protected AbstractOrder(SymbolCode symbolCode, double quantity) {
        id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        orderStatus = OrderStatus.OPEN;
        orderOpenAt = LocalDateTime.now();
        orderExpirationAt = orderOpenAt.plus(expirationTime, ChronoUnit.MILLIS);
        this.quantity = quantity;
        this.symbolCode = symbolCode;
    }

    protected AbstractOrder() {
        id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        orderStatus = OrderStatus.OPEN;
        orderOpenAt = LocalDateTime.now();
        orderExpirationAt = orderOpenAt.plus(expirationTime, ChronoUnit.MILLIS);
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
            return this.orderOpenAt.compareTo(other.orderOpenAt);
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