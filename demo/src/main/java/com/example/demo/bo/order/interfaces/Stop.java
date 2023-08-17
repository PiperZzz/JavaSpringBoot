package com.example.demo.bo.order.interfaces;

public interface Stop extends Order {   
    void setStopPrice(double stopPrice);
    void triggerStop();
}