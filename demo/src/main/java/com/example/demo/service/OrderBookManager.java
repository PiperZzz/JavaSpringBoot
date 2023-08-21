package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.demo.bo.OrderBook;
import com.example.demo.enums.SymbolCode;

@Service
public class OrderBookManager {
     private static Map<SymbolCode, OrderBook> orderBooks = new ConcurrentHashMap<>();

    private OrderBookManager() {
        for (SymbolCode symbolCode : SymbolCode.values()) {
            orderBooks.put(symbolCode, new OrderBook(symbolCode));
        }
    }

    public static void addOrderBook(SymbolCode symbolCode) {
        orderBooks.putIfAbsent(symbolCode, new OrderBook(symbolCode));
    }

    public static OrderBook getOrderBook(SymbolCode symbolCode) {
        return orderBooks.get(symbolCode);
    }
}