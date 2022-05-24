package com.app.NFT.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idt")
@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idt;
	
	@Column(nullable = false, length = 45)
	private Date date;
	
	@Column(nullable = false, length = 45)
	private double price;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@JoinColumn(nullable = false, name = "buyer", referencedColumnName = "idu")
	private User buyer;
	
	@ManyToOne
	@JoinColumn(name = "seller", referencedColumnName = "idu")
	private User seller;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@JoinColumn(name = "id_nft", referencedColumnName = "idn")
	private NFT nft;

	public Transaction(int idt, Date date, double price, User buyer, User seller, NFT nft) {
		super();
		this.idt = idt;
		this.date = date;
		this.price = price;
		this.buyer = buyer;
		this.seller = seller;
		this.nft = nft;
	}
	
	

	

}
