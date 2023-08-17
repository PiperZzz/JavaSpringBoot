package com.example.demo.bo.order.interfaces.directions;

import com.example.demo.bo.order.interfaces.Order;
import com.example.demo.enums.OrderDirection;

public interface Buy extends Order {
    public static final OrderDirection orderDirection = OrderDirection.BUY;
    void executeOrder();
}