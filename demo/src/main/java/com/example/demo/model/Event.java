package com.example.demo.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Event {
    private long orderEventId;
}