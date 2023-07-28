package com.example.demo.bo;

import java.beans.ConstructorProperties;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.enums.OrderType;
import com.example.demo.enums.Symbol;
import com.example.demo.service.EventStore;

public class OrderBook {
    private static final Logger logger = LoggerFactory.getLogger(OrderBook.class);

    private Symbol symbol;
    private PriorityQueue<Order> buyOrders = new PriorityQueue<>();
    private PriorityQueue<Order> sellOrders = new PriorityQueue<>();

    private EventStore eventStore;

    public OrderBook(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @ConstructorProperties({"symbol"})
    public OrderBook(Symbol symbol) {
        this.symbol = symbol;
    }

    public void addOrder(Order order) {
        if (!order.getSymbol().equals(symbol)) {
            throw new IllegalArgumentException("Order symbol does not match order book symbol");
        }

        if (order.getType().equals(OrderType.BUY)) {
            buyOrders.add(order);
        } else if (order.getType().equals(OrderType.SELL)) {
            sellOrders.add(order);
        } else if (order.getType().equals(OrderType.CANCEL)) {
            buyOrders.remove(order);
            sellOrders.remove(order);
        } //TODO Handle other order types

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId(order.getId());

        eventStore.saveEvent(orderEvent);

        matchOrders();
    }

    private void matchOrders() {
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            Order buyOrder = buyOrders.peek();
            Order sellOrder = sellOrders.peek();

            if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                double tradedAmount = Math.min(buyOrder.getAmount(), sellOrder.getAmount());

                logger.info("Matched {} at price {}", tradedAmount, buyOrder.getPrice());

                buyOrder.setAmount(buyOrder.getAmount() - tradedAmount);
                sellOrder.setAmount(sellOrder.getAmount() - tradedAmount);

                if (buyOrder.getAmount() <= 0) {
                    buyOrders.poll();
                }

                if (sellOrder.getAmount() <= 0) {
                    sellOrders.poll();
                }
            } else {
                break;
            }
        }
    }
}