package com.app.NFT.service;

import java.util.List;

import com.app.NFT.entities.NFT;
import com.app.NFT.entities.User;

public interface INFTService {

public List<NFT> SelAll();
	
	public NFT SelByIdNFT(int IdNFT);
	
//	public User SelByAnnoCodice(int Anno, String Codice);
//	
//	List<User> SelActivePromo(Date Data);
	
	public void InsNFT(NFT nft);
	
	public void DelNFT(NFT nft);
}
	

