package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.enums.OrderType;
import com.example.demo.factory.LimitBuyOrderFactory;
import com.example.demo.factory.MarketBuyOrderFactory;
import com.example.demo.factory.StopLimitBuyOrderFactory;
import com.example.demo.factory.StopMarketBuyOrderFactory;
import com.example.demo.factory.interfaces.OrderFactory;

@Configuration
public class FactoryConfig {
    
    @Bean
    public Map<OrderType, OrderFactory> orderFactoryMap() {
        Map<OrderType, OrderFactory> map = new HashMap<>();
        map.put(OrderType.LIMIT_BUY, new LimitBuyOrderFactory());
        map.put(OrderType.MARKET_BUY, new MarketBuyOrderFactory());
        map.put(OrderType.STOP_LIMIT_BUY, new StopLimitBuyOrderFactory());
        map.put(OrderType.STOP_MARKET_BUY, new StopMarketBuyOrderFactory());
        return map;
    }
}