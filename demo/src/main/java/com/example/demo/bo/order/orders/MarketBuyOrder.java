package com.example.demo.bo.order.orders;

import java.time.LocalDateTime;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.Market;
import com.example.demo.bo.order.interfaces.directions.Buy;
import com.example.demo.enums.OrderStatus;

public class MarketBuyOrder extends AbstractOrder implements Market, Buy {

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
        orderCloseAt = LocalDateTime.now();
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