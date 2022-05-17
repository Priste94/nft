package com.app.NFT.dto;

import java.util.Date;

import com.app.NFT.entities.NFT;
import com.app.NFT.entities.User;

import lombok.Data;

@Data
public class TransactionDTO {
	
	private Date date;
	private double price;
	private UserDTO buyer;
	private UserDTO seller;
	private NFT nft;

	
}
