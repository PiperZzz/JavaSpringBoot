package com.example.demo.model;

import com.example.demo.enums.Symbol;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class OrderBook {
    private long orderBookId;
    private Symbol symbol;
}