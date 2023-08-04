package com.example.demo.bo;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public abstract class Event {
    private long id;
    private UUID uuid;
    private String name;
    private LocalDateTime timestamp;

    protected Event(String name) {
        this.timestamp = LocalDateTime.now();
        this.uuid = UUID.randomUUID();
        this.name = name;
    }
}