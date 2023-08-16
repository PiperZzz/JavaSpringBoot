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

    public StopLImitBuyOrder(long id, SymbolCode symbol, double price, double quantity) {
        super(id, symbol, price, quantity);
    }

    public StopLImitBuyOrder(long id, SymbolCode symbol, double price, double quantity, double limitPrice) {
        super(id, symbol, price, quantity);
        this.limitPrice = limitPrice;
    }

    public StopLImitBuyOrder() {
        super();
    }

    @Override
    public double setExecutionPrice() {
        return limitPrice;
    }

    @Override
    public void setDirection() {
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
