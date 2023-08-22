package com.example.demo.service;

import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.bo.OrderBook;
import com.example.demo.bo.order.AbstractOrder;
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

    @Scheduled(fixedRate = 1000)
    public void activateStopOrders() {
        Map<SymbolCode, OrderBook> orderBooks = OrderBookManager.getOrderBooks();
        for (OrderBook orderBook : orderBooks.values()) {
            orderBook.activateStopOrders();
        }
    }

    public void pageOrders() {
        //TODO page orders
    }

    @EventListener
    public void updateOrderBookOnChange (OrderBookEvent orderBookEvent) {
        SymbolCode symbolCode = orderBookEvent.getOrderBookSymbolCode();
        OrderBook orderBook = OrderBookManager.getOrderBook(symbolCode);

        PriorityQueue<AbstractOrder> buyOrders = orderBook.getBuyOrders();
        PriorityQueue<AbstractOrder> sellOrders = orderBook.getSellOrders();

        matchOrdersAndExecuteTrades(buyOrders, sellOrders);
    }

    private void matchOrdersAndExecuteTrades(PriorityQueue<AbstractOrder> buyOrders, PriorityQueue<AbstractOrder> sellOrders) {
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            AbstractOrder buyOrder = buyOrders.peek();
            AbstractOrder sellOrder = sellOrders.peek();
    
            if (buyOrder.getExecutionPrice() >= sellOrder.getExecutionPrice()) {
                double tradeQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
    
                buyOrder.setQuantity(buyOrder.getQuantity() - tradeQuantity);
                sellOrder.setQuantity(sellOrder.getQuantity() - tradeQuantity);
    
                if (buyOrder.getQuantity() <= 0) {
                    buyOrders.poll();
                }
    
                if (sellOrder.getQuantity() <= 0) {
                    sellOrders.poll();
                }
    
                //Order Loogic, Trade History, etc.
            } else {
                break;
            }
        }
    }
}