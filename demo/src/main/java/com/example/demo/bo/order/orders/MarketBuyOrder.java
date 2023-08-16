package com.example.demo.bo.order.orders;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.BuySide;
import com.example.demo.bo.order.interfaces.MarketOrder;
import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.SymbolCode;

public class MarketBuyOrder extends AbstractOrder implements MarketOrder, BuySide {
    private double marketPrice;

    public MarketBuyOrder(long id, SymbolCode symbol, double quantity) {
        super(id, symbol, quantity);
    }

    public MarketBuyOrder() {
        super();
    }

    @Override
    public double setExecutionPrice() {
        return marketPrice;
    }

    @Override
    public void setDirection() {
        this.orderDirection = OrderDirection.BUY;
    }

    @Override
    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
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
