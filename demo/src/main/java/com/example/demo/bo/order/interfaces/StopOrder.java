package com.example.demo.bo.order.interfaces;

public interface StopOrder {
    void setTriggerPrice(double triggerPrice);
    void executeStopOrder();
}