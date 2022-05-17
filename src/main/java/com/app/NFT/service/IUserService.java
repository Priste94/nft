package com.app.NFT.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.NFT.entities.User;

public interface IUserService {
	
	public List<User> SelAll();
	
	public User SelByIdUser(int IdUser);
	
//	public User SelByAnnoCodice(int Anno, String Codice);
//	
//	List<User> SelActivePromo(Date Data);
	
	public ResponseEntity<User> InsUser(User user);
	
	public void DelUser(User user);
}
