package com.example.demo.bo.order.orders;

import java.time.LocalDateTime;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.Market;
import com.example.demo.bo.order.interfaces.Stop;
import com.example.demo.bo.order.interfaces.directions.Buy;
import com.example.demo.enums.OrderStatus;

public class StopMarketBuyOrder extends AbstractOrder implements Stop, Market, Buy {
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
        orderCloseAt = LocalDateTime.now();
        orderStatus = OrderStatus.CLOSE;
    }

    @Override
    public void setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
    }

    @Override
    public void triggerStop() {
        isStopTriggered = true;
        orderUpdateAt = LocalDateTime.now();
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