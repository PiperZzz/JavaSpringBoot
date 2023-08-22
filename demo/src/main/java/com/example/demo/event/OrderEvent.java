package com.example.demo.event;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.event.interfaces.Event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderEvent extends ApplicationEvent implements Event {
    private long id;
    private AbstractOrder order;

    public OrderEvent() {
        super(new Object());
        this.id = UUID.randomUUID().getMostSignificantBits();
    }

    public OrderEvent(AbstractOrder order) {
        super(order);
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.order = order;
    }
}