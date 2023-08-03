package com.example.demo.service;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import com.example.demo.bo.OrderEvent;
import com.example.demo.enums.MessageTopic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventStore {
    private final KafkaTemplate<String, String> kafkaTemplateStringMsg;
    private final KafkaTemplate<String, Object> kafkaTemplateObjectMsg;

    public EventStore(KafkaTemplate<String, String> kafkaTemplateStringMsg, KafkaTemplate<String, Object> kafkaTemplateObjectMsg) {
        this.kafkaTemplateStringMsg = kafkaTemplateStringMsg;
        this.kafkaTemplateObjectMsg = kafkaTemplateObjectMsg;
    }

    @PostConstruct
    public void eventStoreInit() {
        kafkaTemplateStringMsg.send(MessageTopic.APPLICATION_EVENT.name(), "Trading Application Server - OrderStore Service Initialized");
    }

    @PostConstruct
    public void testSaveEvent() {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderOpenTime(LocalDateTime.now());
        kafkaTemplateObjectMsg.send(MessageTopic.ORDER_EVENT.name(), orderEvent);
    }

    public void saveEvent(OrderEvent orderEvent) {
        kafkaTemplateObjectMsg.send(MessageTopic.APPLICATION_EVENT.name(), orderEvent);
    }
}