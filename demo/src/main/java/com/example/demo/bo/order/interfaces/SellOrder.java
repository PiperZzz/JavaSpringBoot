package com.example.demo.bo.order.interfaces;

public interface SellOrder {
    void setSellPrice(double price);
    void setSellQuantity(int quantity);
    void executeSell();
}