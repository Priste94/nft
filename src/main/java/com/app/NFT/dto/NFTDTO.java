package com.app.NFT.dto;

import java.util.Date;

import com.app.NFT.entities.User;
import com.app.NFT.entities.NFT.Status;

import lombok.Data;

@Data
public class NFTDTO {
	
	private String name;
	private String author;
	private double price;
	private Status status;
	private Date dateOfInput;
	private UserDTO ownedBy;

}
