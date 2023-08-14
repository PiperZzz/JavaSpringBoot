package com.example.demo.bo.order.interfaces;

public interface BuyOrder {
    void setBuyPrice(double price);
    void setBuyQuantity(int quantity);
    void executeBuy();
}