package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Wallet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @OneToMany(mappedBy = "wallet")
    private List<Asset> assets;
    
    private Double balance;
    private String walletStatus;
    private String walletType;
    private String walletName;
    private String walletPassword;
    private String walletPublicKey;
}