package com.app.NFT.entities;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;






@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idn")
@Entity
@Table(name = "nft")
public class NFT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idn;
	
	@Column(nullable = false, unique = true, length = 45)
	private String url;
	

	@Column(nullable = false, length = 45)
	private double price;
	
	@Column(nullable = false, columnDefinition = "ENUM('SOLD', 'ON_SALE')")
	@Enumerated(EnumType.STRING)
	private Status status;

	
	@Column(nullable = false, length = 45)
	private String name;
	
	@Column(nullable = false, length = 45)
	private String author;
	
	@Column(nullable = false, length = 45)
	private Date dateOfInput;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,  mappedBy = "nft", orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Transaction> transactions = new HashSet<>();
	
	@ManyToOne
	@JsonBackReference // =@JsonIgnore
	@JoinColumn(name = "ownedBy", referencedColumnName = "idu")
	private User ownedBy;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "favorites")
	    private Set<User> users = new HashSet<>();
	
	public NFT(int id, String url) {
		
		this.idn = id;
		this.url = url;
	}

	public NFT(int idn, Status status) {
		super();
		this.idn = idn;
		this.status = status;
	}
	
	public enum Status {
	    SOLD,
	    ON_SALE;
	}
	
	public void changeStatus() {
		if (this.status==Status.ON_SALE)
			this.status = Status.SOLD;
		else
			this.status = Status.ON_SALE;
	}

	public NFT(int idn) {
		super();
		this.idn = idn;
	}
	
	
	
	
}
