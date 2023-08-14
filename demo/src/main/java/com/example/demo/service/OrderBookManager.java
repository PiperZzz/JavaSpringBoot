package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.bo.OrderBook;
import com.example.demo.enums.SymbolCode;

@Service
public class OrderBookManager {
    private Map<SymbolCode, OrderBook> orderBooks = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        List<SymbolCode> symbols = loadSymbolPairs();

        for (SymbolCode symbol : symbols) {
            orderBooks.put(symbol, new OrderBook(symbol));
        }
    }

    public OrderBook getOrderBook(SymbolCode symbol) {
        return orderBooks.computeIfAbsent(symbol, k -> new OrderBook(symbol));
    }

    public void addSymbolPair(SymbolCode symbol) {
        orderBooks.putIfAbsent(symbol, new OrderBook(symbol));
    }

    private List<SymbolCode> loadSymbolPairs() {
        //TODO Load the list of symbol pairs from database
        return Arrays.asList(SymbolCode.values());
    }
}