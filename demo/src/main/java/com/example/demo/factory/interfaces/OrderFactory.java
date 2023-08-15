package com.example.demo.factory.interfaces;

import com.example.demo.bo.order.AbstractOrder;

public interface OrderFactory {
    AbstractOrder createOrder();
}