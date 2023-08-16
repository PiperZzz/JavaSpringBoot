package com.example.demo.factory;

import com.example.demo.bo.order.orders.StopLimitBuyOrder;
import com.example.demo.factory.interfaces.OrderFactory;

public class StopLimitBuyOrderFactory implements OrderFactory{
    @Override
    public StopLimitBuyOrder createOrder() {
        return new StopLimitBuyOrder();
    }
}