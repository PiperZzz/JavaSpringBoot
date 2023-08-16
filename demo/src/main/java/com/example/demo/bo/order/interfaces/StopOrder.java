package com.example.demo.bo.order.interfaces;

public interface StopOrder {   
    void setStopPrice(double stopPrice);
    void triggerStop();
}