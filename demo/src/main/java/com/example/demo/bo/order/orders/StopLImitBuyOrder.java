package com.example.demo.bo.order.orders;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.BuySide;
import com.example.demo.bo.order.interfaces.LimitOrder;
import com.example.demo.bo.order.interfaces.StopOrder;
import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.SymbolCode;

public class StopLImitBuyOrder extends AbstractOrder implements StopOrder, LimitOrder, BuySide {
    private double limitPrice;
    private double stopPrice;

    public StopLImitBuyOrder() {
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
    public void setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
    }

    @Override
    public void triggerStop() {
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
