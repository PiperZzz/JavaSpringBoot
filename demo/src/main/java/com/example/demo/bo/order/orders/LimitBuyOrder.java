package com.example.demo.bo.order.orders;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.interfaces.BuySide;
import com.example.demo.bo.order.interfaces.LimitOrder;
import com.example.demo.enums.OrderStatus;

public class LimitBuyOrder extends AbstractOrder implements LimitOrder, BuySide {

    public LimitBuyOrder() {
        super();
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
