package com.example.demo.bo.order.interfaces.directions;

import com.example.demo.bo.order.interfaces.Order;
import com.example.demo.enums.OrderDirection;

public interface Sell extends Order {
    public static final OrderDirection orderDirection = OrderDirection.SELL;
    void executeOrder();
}