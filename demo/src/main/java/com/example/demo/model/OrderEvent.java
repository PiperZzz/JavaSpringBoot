package com.example.demo.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class OrderEvent {
    private long orderEventId;
}