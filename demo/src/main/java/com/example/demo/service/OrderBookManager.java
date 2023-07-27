package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.demo.bo.OrderBook;

@Service
public class OrderBookManager {
    private Map<String, OrderBook> orderBooks = new ConcurrentHashMap<>();

    public OrderBook getOrderBook(String symbol) {
        return orderBooks.computeIfAbsent(symbol, k -> new OrderBook());
    }
}