package com.app.NFT;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.NFT.entities.NFT;
import com.app.NFT.entities.Transaction;
import com.app.NFT.repository.NFTRepository;
import com.app.NFT.repository.TransactionRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Nft1Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionRepositoryTest {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Test
	public void TestFindByIdt() {
		
		int id = 3;
		assertThat(transactionRepository.findByIdt(id))
		.extracting(Transaction::getPrice)
		.isEqualTo(10.0);

	}
	
	@Test
	public void TestGetTransactionsByUserId() {
		
		List<Transaction> transactions = transactionRepository.getTransactionsByUserId(1);
		
		for(int i=0; i<transactions.size()-1; ++i) 
			
			assertEquals(transactions.get(i).getBuyer().getIdu(), 1);
	
		assertEquals(transactions.get(6).getSeller().getIdu(), 1);
			
	}
	
	@Test
	public void TestGetAcquisitionsByUserId() {
		
		List<Transaction> transactions = transactionRepository.getAcquisitionsByUserId(1);
		
		for(int i=0; i<transactions.size(); ++i) 
			
			assertEquals(transactions.get(i).getBuyer().getIdu(), 1);
	
			
	}
	
	@Test
	public void TestGetSalesByUserId() {
		
		List<Transaction> transactions = transactionRepository.getSalesByUserId(1);
		
		for(int i=0; i<transactions.size(); ++i) 
			
			assertEquals(transactions.get(i).getSeller().getIdu(), 1);
	
			
	}
	
}
