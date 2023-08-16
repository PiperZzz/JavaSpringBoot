package com.example.demo.factory;

import com.example.demo.bo.order.orders.StopMarketBuyOrder;
import com.example.demo.factory.interfaces.OrderFactory;

public class StopMarkeBuyOrderFactory implements OrderFactory {
    @Override
    public StopMarketBuyOrder createOrder() {
        return new StopMarketBuyOrder();
    }
}