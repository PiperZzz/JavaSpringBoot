package com.example.demo.bo.order.orders;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.BuySide;
import com.example.demo.bo.order.interfaces.MarketOrder;
import com.example.demo.enums.OrderStatus;

public class MarketBuyOrder extends AbstractOrder implements MarketOrder, BuySide {

    public MarketBuyOrder() {
        super();
    }

    @Override
    public double getExecutionPrice() {
        return excutionPrice;
    }

    @Override
    public void setExecutionPrice(double marketPrice) {
        excutionPrice = marketPrice;
    }

    @Override
    public void executeOrder() {
        orderStatus = OrderStatus.CLOSE;
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
