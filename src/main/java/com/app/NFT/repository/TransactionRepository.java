package com.app.NFT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.NFT.entities.NFT;
import com.app.NFT.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	public Transaction findByIdt(int id);

    @Query(value="SELECT DISTINCT t.* FROM Transactions t WHERE t.buyer = ?1 OR t.seller = ?1", nativeQuery=true)
	public List<Transaction>  getTransactionsByUserId(int idUser);


	@Query(value="SELECT DISTINCT t.* FROM Transactions t WHERE t.buyer = ?1", nativeQuery=true)
	public List<Transaction> getAcquisitionsByUserId(int idUser);
	
	
	@Query(value="SELECT DISTINCT t.* FROM Transactions t WHERE t.seller = ?1", nativeQuery=true)
	public List<Transaction> getSalesByUserId(int idUser);

}