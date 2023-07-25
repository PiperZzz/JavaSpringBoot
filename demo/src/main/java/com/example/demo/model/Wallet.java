package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Wallet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    private String cryptocurrency;
    private Double balance;
    private String walletAddress;
    private String walletStatus;
    private String walletType;
    private String walletName;
    private String walletPassword;
    private String walletPublicKey;
}