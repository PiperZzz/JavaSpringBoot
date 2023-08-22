package com.example.demo.bo;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.enums.OrderBookEventType;
import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.SymbolCode;
import com.example.demo.event.OrderBookEvent;

@Component
public class OrderBook {
    private static final Logger logger = LoggerFactory.getLogger(OrderBook.class);
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    private final SymbolCode symbolCode;
    private PriorityQueue<AbstractOrder> buyOrders = new PriorityQueue<>();
    private PriorityQueue<AbstractOrder> sellOrders = new PriorityQueue<>();

    @ConstructorProperties({"symbolCode"})
    public OrderBook(SymbolCode symbolCode) {
        this.symbolCode = symbolCode;
    }

    public void addOrder(AbstractOrder order) {
        if (!order.getSymbolCode().equals(symbolCode)) {
            throw new IllegalArgumentException("Order symbol does not match order book symbol");
        }

        if (order.getOrderDirection().equals(OrderDirection.BUY)) {
            buyOrders.add(order);
        } else if (order.getOrderDirection().equals(OrderDirection.SELL)) {
            sellOrders.add(order);            
        } else {
            throw new IllegalArgumentException("Invalid order direction");
        }

        applicationEventPublisher.publishEvent(new OrderBookEvent(order, OrderBookEventType.ADD, symbolCode));
    }

    public void cleanupExpiredOrders() {
        LocalDateTime currentTime = LocalDateTime.now();
        
        cleanupExpiredOrdersInQueue(buyOrders, currentTime);
        cleanupExpiredOrdersInQueue(sellOrders, currentTime);
    }

    public void activateStopOrders() {
        LocalDateTime currentTime = LocalDateTime.now();
        
        activateStopOrdersInQueue(buyOrders, currentTime);
        activateStopOrdersInQueue(sellOrders, currentTime);
    }

    private void cleanupExpiredOrdersInQueue(PriorityQueue<AbstractOrder> orders, LocalDateTime currentTime) {
        Iterator<AbstractOrder> iterator = orders.iterator();

        while (iterator.hasNext()) {
            AbstractOrder order = iterator.next();
            if (order.getOrderExpirationAt().isBefore(currentTime)) {
                iterator.remove();
                logger.info("Order {} expired", order.getId());
            }
        }
    }

    private void activateStopOrdersInQueue(PriorityQueue<AbstractOrder> orders, LocalDateTime currentTime) {
        //TODO implement this method
    }
}