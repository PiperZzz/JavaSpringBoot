package com.example.demo.factory;

import org.springframework.stereotype.Component;

import com.example.demo.bo.order.orders.MarketBuyOrder;
import com.example.demo.factory.interfaces.OrderFactory;
@Component
public class MarketBuyOrderFactory implements OrderFactory {
    @Override
    public MarketBuyOrder createOrder() {
        return new MarketBuyOrder();
    }
}