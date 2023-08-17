package com.example.demo.bo.order.interfaces.directions;

import com.example.demo.enums.OrderDirection;

public interface Buy {
    public static final OrderDirection orderDirection = OrderDirection.BUY;
    void executeOrder();
}