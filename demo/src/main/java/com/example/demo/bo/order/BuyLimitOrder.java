package com.example.demo.bo.order;

import com.example.demo.bo.order.interfaces.BuySide;
import com.example.demo.bo.order.interfaces.LimitOrder;
import com.example.demo.enums.OrderType;
import com.example.demo.enums.Symbol;

public class BuyLimitOrder extends AbstractOrder implements BuySide, LimitOrder {
    private double limitPrice;

    public BuyLimitOrder(long id, Symbol symbol, double price, double quantity) {
        super(id, symbol, price, quantity);
    }

    @Override
    public void setDirection() {
        this.orderDirection = OrderType.BUY;
    }

    @Override
    public void setLimitPrice(double limitPrice) {
        this.limitPrice = limitPrice;
    }

    @Override
    public void executeBuy() {
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
