package com.example.demo.factory;

import org.springframework.stereotype.Component;

import com.example.demo.bo.order.orders.StopLimitBuyOrder;
import com.example.demo.factory.interfaces.OrderFactory;
@Component
public class StopLimitBuyOrderFactory implements OrderFactory{
    @Override
    public StopLimitBuyOrder createOrder() {
        return new StopLimitBuyOrder();
    }
}