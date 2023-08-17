package com.example.demo.bo.order.interfaces.directions;

import com.example.demo.enums.OrderDirection;

public interface Sell {
    public static final OrderDirection orderDirection = OrderDirection.SELL;
    void executeOrder();
}