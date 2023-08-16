package com.example.demo.bo.order.orders;

import java.time.LocalDateTime;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.BuySide;
import com.example.demo.bo.order.interfaces.LimitOrder;
import com.example.demo.bo.order.interfaces.StopOrder;
import com.example.demo.enums.OrderStatus;

public class StopLimitBuyOrder extends AbstractOrder implements StopOrder, LimitOrder, BuySide {
    private double stopPrice;
    private boolean isStopTriggered;

    public StopLimitBuyOrder() {
        super();
        isStopTriggered = false;
    }

    @Override
    public double getExecutionPrice() {
        return excutionPrice;
    }

    @Override
    public void setExecutionPrice(double limitPrice) {
        excutionPrice = limitPrice;
    }

    @Override
    public void executeOrder() {
        orderCloseTime = LocalDateTime.now();
        orderStatus = OrderStatus.CLOSE;
    }

    @Override
    public void setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
    }

    @Override
    public void triggerStop() {
        isStopTriggered = true;
        orderLastUpdateTime = LocalDateTime.now();
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
