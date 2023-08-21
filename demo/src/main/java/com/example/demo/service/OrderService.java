package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.Wallet;
import com.example.demo.util.EnumUtil;
import com.example.demo.model.Asset;
import com.example.demo.bo.order.AbstractOrder;
import com.example.demo.controller.dto.OrderRequest;
import com.example.demo.dao.AssetRepository;
import com.example.demo.dao.UserOrderRepository;
import com.example.demo.dao.WalletRepository;
import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.OrderType;
import com.example.demo.enums.SymbolCode;
import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.factory.interfaces.OrderFactory;

@Service
public class OrderService {
    private static final Logger logger= LoggerFactory.getLogger(OrderService.class);

    private final WalletRepository walletRepository;
    private final UserOrderRepository userOrderRepository;
    private final AssetRepository assetRepository;
    
    @Autowired
    private final Map<OrderType, OrderFactory> orderFactoryMap;

    public OrderService(WalletRepository walletRepository, UserOrderRepository userOrderRepository, AssetRepository assetRepository, Map<OrderType, OrderFactory> orderFactoryMap) {
        this.walletRepository = walletRepository;
        this.userOrderRepository = userOrderRepository;
        this.assetRepository = assetRepository;
        this.orderFactoryMap = orderFactoryMap;
    }

    public void createOrder(User user, OrderRequest orderRequest) {
        logger.info("Create Order for User: {}, Request: {}", user, orderRequest);

        OrderType orderType = EnumUtil.getEnum(OrderType.class, orderRequest.getOrderType());
        if (orderType == null) {
            throw new IllegalArgumentException("Invalid order type");
        }

        AbstractOrder abstractOrder = orderFactoryMap.get(orderType).createOrder();
        abstractOrder.setSymbolCode(EnumUtil.getEnum(SymbolCode.class, orderRequest.getSymbol()));
        abstractOrder.setOrderDirection(EnumUtil.getEnum(OrderDirection.class, orderRequest.getOrderDirection()));
        abstractOrder.setQuantity(orderRequest.getQuantity());
        abstractOrder.setExcutionPrice(orderRequest.getExcutionPrice());
        abstractOrder.setExpirationTime(orderRequest.getExpirationTime());

        //TODO hand over the order to order book manager

    }

    public void cancelOrder(User user, String symbol, double amount, double price) {
        logger.info("User {} Selling {} Amount of {} at Price {}", user, amount, symbol, price);
        //TODO Refactor this method for general order cancellation

        Wallet wallet = walletRepository.findByUser(user);
        List<Asset> assets = assetRepository.findBySymbol(wallet);
        Optional<Asset> result = assets.stream().filter(asset -> asset.getSymbol().equals(SymbolCode.valueOf(symbol))).findFirst();
        if (result.isPresent()) {
            Asset asset = result.get();
            if (asset.getAmount() < amount) {
                throw new InsufficientBalanceException();
            }
            asset.setAmount(asset.getAmount() - amount);
            assetRepository.save(asset);
        } else {
            throw new InsufficientBalanceException();
        }
    }
}