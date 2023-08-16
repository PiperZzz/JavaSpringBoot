package com.example.demo.bo.order.orders;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.BuySide;
import com.example.demo.bo.order.interfaces.LimitOrder;
import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.SymbolCode;

public class LimitBuyOrder extends AbstractOrder implements LimitOrder, BuySide {
    private double limitPrice;

    public LimitBuyOrder() {
        super();
    }

    @Override
    public double getExecutionPrice() {
        return limitPrice;
    }

    @Override
    public void setOrderDirection() {
        this.orderDirection = OrderDirection.BUY;
    }

    @Override
    public void setExecutionPrice(double limitPrice) {
        this.limitPrice = limitPrice;
    }

    @Override
    public void executeOrder() {
        //TODO
    }

    @Override
    public boolean equals(Object other)  {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
