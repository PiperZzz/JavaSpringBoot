package com.example.demo.bo.order.interfaces;

import com.example.demo.enums.OrderDirection;

public interface BuySide {
    public static final OrderDirection orderDirection = OrderDirection.BUY;
    void executeOrder();
}