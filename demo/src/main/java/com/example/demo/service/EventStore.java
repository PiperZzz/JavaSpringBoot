package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demo.bo.OrderEvent;
import com.example.demo.enums.MessageTopic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventStore {
    private List<OrderEvent> orderEvents = new ArrayList<>();
    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventStore(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    public void eventStoreInit() {
        kafkaTemplate.send(MessageTopic.APPLICATION_EVENT.name(), "OrderStore Initialized");
    }

    public void saveEvent(OrderEvent orderEvent) {
        orderEvents.add(orderEvent);
        kafkaTemplate.send("order-events", orderEvent.toString());
    }
}