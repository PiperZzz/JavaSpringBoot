package com.example.demo.factory;

import org.springframework.stereotype.Component;

import com.example.demo.bo.order.orders.StopMarketBuyOrder;
import com.example.demo.factory.interfaces.OrderFactory;
@Component
public class StopMarketBuyOrderFactory implements OrderFactory {
    @Override
    public StopMarketBuyOrder createOrder() {
        return new StopMarketBuyOrder();
    }
}