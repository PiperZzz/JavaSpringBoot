package com.example.demo.service;

import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.bo.OrderBook;
import com.example.demo.enums.SymbolCode;
import com.example.demo.event.OrderBookEvent;

@Service
public class OrderBookSweeper {

    @Scheduled(fixedRate = 1000)
    public void cleanupExpiredOrders() {
        Map<SymbolCode, OrderBook> orderBooks = OrderBookManager.getOrderBooks();
        for (OrderBook orderBook : orderBooks.values()) {
            orderBook.cleanupExpiredOrders();
        }
    }

    @EventListener
    public void updateOrderBook (OrderBookEvent orderBookEvent) {
        OrderBookManager.getOrderBook(orderBookEvent.getOrderBookSymbolCode());
        //TODO update order book
    }
}