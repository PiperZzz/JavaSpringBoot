package com.example.demo.bo.order.orders;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.BuySide;
import com.example.demo.bo.order.interfaces.LimitOrder;
import com.example.demo.bo.order.interfaces.StopOrder;
import com.example.demo.enums.OrderStatus;

public class StopLImitBuyOrder extends AbstractOrder implements StopOrder, LimitOrder, BuySide {
    private double stopPrice;
    private boolean isStopTriggered;

    public StopLImitBuyOrder() {
        super();
        isStopTriggered = false;
    }

    @Override
    public double getExecutionPrice() {
        return price;
    }

    @Override
    public void setExecutionPrice(double limitPrice) {
        price = limitPrice;
    }

    @Override
    public void executeOrder() {
        //TODO
        orderStatus = OrderStatus.CLOSE;
    }

    @Override
    public void setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
    }

    @Override
    public void triggerStop() {
        this.isStopTriggered = true;
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
