package com.example.demo.factory;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.bo.order.orders.MarketBuyOrder;
import com.example.demo.factory.interfaces.OrderFactory;

public class MarketBuyOrderFactory implements OrderFactory {
    @Override
    public AbstractOrder createOrder() {
        return new MarketBuyOrder();
    }
}