package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.bo.OrderEvent;

import org.springframework.stereotype.Service;

@Service
public class EventStore {
    private List<OrderEvent> orderEvents = new ArrayList<>();

    public void saveEvent(OrderEvent orderEvent) {
        //TODO Save the event to database
        orderEvents.add(orderEvent);
    }

    public List<OrderEvent> getOrderEvents() {
        //TODO Load the events from database
        return orderEvents;
    }
}