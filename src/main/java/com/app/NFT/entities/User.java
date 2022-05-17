package com.app.NFT.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idu")
@Table(name = "users")
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idu;
     
    @Column(name = "username", nullable = false, unique = true, length = 45)
    //@JsonIgnore 
    private String userName;
     
    @Column(name = "password", nullable = false, length = 64)
    @JsonBackReference //tutti questi jsonignore mi creano problemi al momento dell' inserimento di un nuovo utente perchè anche in input non vengono considerati i campi jsonignore
    private String password;
     
    @Column(name = "name", nullable = false, length = 64)

    private String name;
    
    @Column(name = "wallet", nullable = false)
    //@JsonBackReference
    private double wallet;
    
    
    @OneToMany(mappedBy = "buyer", orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    
    //@JsonInclude
    @JsonIgnore
    //@JsonManagedReference
    //@JsonBackReference
	private Set<Transaction> acquisitions = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "seller", orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    //@JsonManagedReference
    //@JsonBackReference
	private Set<Transaction> sales = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "ownedBy", orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    //@JsonManagedReference
    //@JsonBackReference
	private Set<NFT> nft = new HashSet<>();
    
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,  mappedBy = "utente", orphanRemoval = true)
//    @EqualsAndHashCode.Exclude
//    //@JsonManagedReference
//    //@JsonBackReference
//	private Set<NFT> nftPreferiti = new HashSet<>();
    
    
    
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "userFavorites", 
        joinColumns = { @JoinColumn(name = "idu") }, 
        inverseJoinColumns = { @JoinColumn(name = "idn") }
    )
    Set<NFT> favorites = new HashSet<>();
    
    public void decreaseWallet(double money) {
    	//lanceremo un' eccezione se sono insufficienti i fondi
    	if (this.wallet >= money)
    		this.wallet -= money;
    }
    
    public void increasesWallet(double money) {
    		this.wallet += money;
    }

	public User(int idu) {
		super();
		this.idu = idu;
	}


    
}
