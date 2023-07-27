package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.bo.OrderBook;
import com.example.demo.enums.Symbol;

@Service
public class OrderBookManager {
    private Map<Symbol, OrderBook> orderBooks = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        List<Symbol> symbols = loadSymbolPairs();

        for (Symbol symbol : symbols) {
            orderBooks.put(symbol, new OrderBook(symbol));
        }
    }

    public OrderBook getOrderBook(Symbol symbol) {
        return orderBooks.computeIfAbsent(symbol, k -> new OrderBook(symbol));
    }

    public void addSymbolPair(Symbol symbol) {
        orderBooks.putIfAbsent(symbol, new OrderBook(symbol));
    }

    private List<Symbol> loadSymbolPairs() {
        //TODO Load the list of symbol pairs from database
        return Arrays.asList(Symbol.values());
    }
}