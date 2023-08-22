package com.example.demo.service;

import javax.annotation.PostConstruct;

import com.example.demo.enums.MessageTopic;
import com.example.demo.event.OrderBookEvent;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventStore {
    private final KafkaTemplate<String, Object> kafkaTemplateObjectMsg;

    public EventStore(KafkaTemplate<String, Object> kafkaTemplateObjectMsg) {
        this.kafkaTemplateObjectMsg = kafkaTemplateObjectMsg;
    }

    @PostConstruct
    public void eventStoreInit() {
        kafkaTemplateObjectMsg.send(MessageTopic.APPLICATION_EVENT.name(), "Trading Application Server - OrderStore Service Initialized");
    }

    @PostConstruct
    public void testSaveEvent() {
        OrderBookEvent orderEvent = new OrderBookEvent();
        kafkaTemplateObjectMsg.send(MessageTopic.ORDER_EVENT.name(), orderEvent);
    }

    public void saveEvent(OrderBookEvent orderEvent) {
        kafkaTemplateObjectMsg.send(MessageTopic.ORDER_EVENT.name(), orderEvent);
    }
}