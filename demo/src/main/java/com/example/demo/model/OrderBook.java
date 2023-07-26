package com.example.demo.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class OrderBook {
    private long orderBookId;
}