package com.app.NFT.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "nft")
public class NFT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idn;
	
	private String url;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public NFT() {
		
	}
	
	public NFT(int id, String url) {
		
		this.id = id;
		this.url = url;
	}	
}
