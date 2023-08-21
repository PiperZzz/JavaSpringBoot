package com.example.demo.service;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

import com.example.demo.bo.OrderBook;
import com.example.demo.enums.SymbolCode;

@Service
public class OrderBookSweeper {
    private Timer timer;

    public OrderBookSweeper() {
        timer = new Timer();
        scheduleOrderCleanupTask();
    }

    private void scheduleOrderCleanupTask() {
        TimerTask cleanupTask = new OrderCleanupTask();
        timer.scheduleAtFixedRate(cleanupTask, 0, 1000);
    }

    private class OrderCleanupTask extends TimerTask {
        @Override
        public void run() {
            Map<SymbolCode, OrderBook> orderBooks = OrderBookManager.getOrderBooks();
            for (OrderBook orderBook : orderBooks.values()) {
                orderBook.cleanupExpiredOrders();
            }
        }
    }

    public void monitorChanges() {

    }
}