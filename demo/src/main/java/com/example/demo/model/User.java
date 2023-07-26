package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;

    @OneToMany(mappedBy = "buyer")
    private List<Trade> buyerTrades;

    @OneToMany(mappedBy = "seller")
    private List<Trade> sellerTrades;

    @OneToMany(mappedBy = "user")
    private List<TradingOrder> orders;

    private String name;
    private String email;
    private String password;
    private String role;
    private String status;
    private String username;
    private String address;
    private String phoneNumber;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}