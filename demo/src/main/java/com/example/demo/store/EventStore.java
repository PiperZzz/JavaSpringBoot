package com.example.demo.store;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.bo.OrderEvent;

public class EventStore {
    // This could be a database, a file, a message queue, etc.
    private List<OrderEvent> orderEvents = new ArrayList<>();

    public void saveEvent(OrderEvent orderEvent) {
        orderEvents.add(orderEvent);
    }

    public List<OrderEvent> getOrderEvents() {
        return orderEvents;
    }
}