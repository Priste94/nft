package com.app.NFT.API.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.NFT.dto.TransactionDTO;
import com.app.NFT.entities.NFT;
import com.app.NFT.entities.Transaction;
import com.app.NFT.service.NFTService;
import com.app.NFT.service.TransactionService;
import com.app.NFT.service.UserService;

import lombok.NoArgsConstructor;

@NoArgsConstructor

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transazioneService;
		
	
	@GetMapping(value = "/transactions", produces = "application/json") 
	public Iterable<TransactionDTO> getAll() {
		
		return transazioneService.SelAllDTO();
	}
	
	@GetMapping(value = "/transaction/{idTrans}", produces = "application/json")
	public Transaction getById(@PathVariable("idTrans") String IdTrans) {
		return  transazioneService.SelByIdTransaction(Integer.parseInt(IdTrans));
	}
	
	@PostMapping(value = "/transaction/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transazione) {

		return transazioneService.InsTransaction(transazione);
			
	}
	
	@GetMapping(value = "/transaction/{idUser}/transactions", produces = "application/json")
	public List<Transaction> getTransactionsByIdUser(@PathVariable("idUser") String IdUser) {
		return  transazioneService.SelTransazioniByIdUser(Integer.parseInt(IdUser));
	}
	
	
	@GetMapping(value = "/transaction/{idUser}/acquisitions", produces = "application/json")
	public List<Transaction> getAcquisitionsByIdUser(@PathVariable("idUser") String IdUser) {
		return  transazioneService.SelAcquistiByIdUser(Integer.parseInt(IdUser));
	}
	
	@GetMapping(value = "/transaction/{idUser}/sales", produces = "application/json")
	public List<Transaction> getSalesByIdUser(@PathVariable("idUser") String IdUser) {
		return  transazioneService.SelVenditeByIdUser(Integer.parseInt(IdUser));
	}
}
		
		

