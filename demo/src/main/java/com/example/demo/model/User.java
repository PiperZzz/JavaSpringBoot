package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "user")
    private List<Wallet> wallets;

    @OneToMany(mappedBy = "buyer")
    private List<Trade> buyerTrades;

    @OneToMany(mappedBy = "seller")
    private List<Trade> sellerTrades;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    private String name;
    private String email;
    private String password;
    private String role;
    private String status;
    private String username;
}