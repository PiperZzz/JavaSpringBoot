package com.example.demo.event;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.enums.OrderBookEventType;
import com.example.demo.enums.SymbolCode;
import com.example.demo.event.interfaces.Event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderBookEvent extends ApplicationEvent implements Event {
    private long id;
    private OrderBookEventType orderEventType;
    private AbstractOrder order;
    private SymbolCode orderBookSymbolCode;

    public OrderBookEvent() {
        super(new Object());
        this.id = UUID.randomUUID().getMostSignificantBits();
    }

    public OrderBookEvent(AbstractOrder order, OrderBookEventType orderEventType, SymbolCode symbolCode) {
        super(order);
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.order = order;
        this.orderEventType = orderEventType;
        this.orderBookSymbolCode = symbolCode;
    }
}