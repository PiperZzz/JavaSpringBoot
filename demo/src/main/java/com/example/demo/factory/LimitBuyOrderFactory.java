package com.example.demo.factory;

import com.example.demo.bo.order.orders.LimitBuyOrder;
import com.example.demo.factory.interfaces.OrderFactory;

public class LimitBuyOrderFactory implements OrderFactory {
    @Override
    public LimitBuyOrder createOrder() {
        return new LimitBuyOrder();
    }
}