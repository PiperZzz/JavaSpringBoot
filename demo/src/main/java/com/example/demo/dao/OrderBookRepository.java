package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.OrderBook;

@Repository
@Transactional
public interface OrderBookRepository extends CrudRepository<OrderBook, Long> {
    // You can define additional methods for querying and managing OrderBooks
}