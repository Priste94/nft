package com.app.NFT.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.NFT.entities.NFT;
import com.app.NFT.entities.Transaction;

public interface ITransactionService {
	
	public List<Transaction> SelAll();
	
	public Transaction SelByIdTransaction(int IdTransazione);
	
//	public User SelByAnnoCodice(int Anno, String Codice);
//	
//	List<User> SelActivePromo(Date Data);
	
	public ResponseEntity<Transaction> InsTransaction(Transaction transazione);

}
