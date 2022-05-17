package com.app.NFT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.NFT.entities.NFT;

public interface NFTRepository extends JpaRepository<NFT, Long> {
	
	

}
