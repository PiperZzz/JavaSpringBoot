package com.example.demo.bo;

import java.beans.ConstructorProperties;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.enums.OrderType;
import com.example.demo.enums.Symbol;
import com.example.demo.service.EventStore;

public class OrderBook {
    private static final Logger logger = LoggerFactory.getLogger(OrderBook.class);

    private Symbol symbol;
    private PriorityQueue<AbstractOrder> buyOrders = new PriorityQueue<>();
    private PriorityQueue<AbstractOrder> sellOrders = new PriorityQueue<>();

    private EventStore eventStore;

    public OrderBook(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @ConstructorProperties({"symbol"})
    public OrderBook(Symbol symbol) {
        this.symbol = symbol;
    }

    public void addOrder(AbstractOrder order) {
        if (!order.getSymbol().equals(symbol)) {
            throw new IllegalArgumentException("Order symbol does not match order book symbol");
        }

        if (order.getOrderDirection().equals(OrderType.BUY)) {
            buyOrders.add(order);
        } else if (order.getOrderDirection().equals(OrderType.SELL)) {
            sellOrders.add(order);
        }

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId(order.getId());

        eventStore.saveEvent(orderEvent);

        matchOrders();
    }

    private void matchOrders() {
        //TOOD move into TradeService
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            AbstractOrder buyOrder = buyOrders.peek();
            AbstractOrder sellOrder = sellOrders.peek();

            if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                double tradedAmount = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

                logger.info("Matched {} at price {}", tradedAmount, buyOrder.getPrice());

                buyOrder.setQuantity(buyOrder.getQuantity() - tradedAmount);
                sellOrder.setQuantity(sellOrder.getQuantity() - tradedAmount);

                if (buyOrder.getQuantity() <= 0) {
                    buyOrders.poll();
                }

                if (sellOrder.getQuantity() <= 0) {
                    sellOrders.poll();
                }
            } else {
                break;
            }
        }
    }
}