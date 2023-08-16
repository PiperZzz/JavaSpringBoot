package com.example.demo.bo.order.orders;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.BuySide;
import com.example.demo.bo.order.interfaces.MarketOrder;
import com.example.demo.bo.order.interfaces.StopOrder;
import com.example.demo.enums.OrderStatus;

public class StopMarketBuyOrder extends AbstractOrder implements StopOrder, MarketOrder, BuySide {
    private double stopPrice;
    private boolean isStopTriggered;

    public StopMarketBuyOrder() {
        super();
        isStopTriggered = false;
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
    public void setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
    }

    @Override
    public void triggerStop() {
        isStopTriggered = true;
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
