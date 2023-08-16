package com.example.demo.bo.order.interfaces;

import com.example.demo.enums.OrderDirection;

public interface SellSide {
    public static final OrderDirection orderDirection = OrderDirection.SELL;
    void executeOrder();
}