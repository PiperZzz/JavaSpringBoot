package com.example.demo.bo.order.orders;

import java.time.LocalDateTime;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.Limit;
import com.example.demo.bo.order.interfaces.directions.Buy;
import com.example.demo.enums.OrderStatus;

public class LimitBuyOrder extends AbstractOrder implements Limit, Buy {

    public LimitBuyOrder() {
        super();
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
