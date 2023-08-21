package com.example.demo.service;

import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.bo.OrderBook;
import com.example.demo.enums.SymbolCode;

@Service
public class OrderBookSweeper {

    @Scheduled(fixedRate = 1000)
    public void cleanupExpiredOrders() {
        Map<SymbolCode, OrderBook> orderBooks = OrderBookManager.getOrderBooks();
        for (OrderBook orderBook : orderBooks.values()) {
            orderBook.cleanupExpiredOrders();
        }
    }

    public void monitorChanges() {

    }
}