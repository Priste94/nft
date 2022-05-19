package com.app.NFT.API.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import com.app.NFT.dto.UserDTO;
import com.app.NFT.dto.UserLoginDTO;
import com.app.NFT.entities.NFT;
import com.app.NFT.entities.Transaction;
import com.app.NFT.entities.User;
import com.app.NFT.service.NFTService;
import com.app.NFT.service.UserService;

import lombok.NoArgsConstructor;

@NoArgsConstructor

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping(value = "/users", produces = "application/json") 
	public Iterable<UserDTO> getAll() {
		
		return userService.SelAllDTO();
	}
	
	@GetMapping(value = "/user/{idUser}", produces = "application/json")
	public User getById(@PathVariable("idUser") int IdUser) {
		return  userService.SelByIdUser(IdUser);
	}
	
	@PostMapping(value = "/user/insert", consumes="application/json")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{

			return userService.InsUser(user);

	}
	
	@PostMapping(value = "/user", consumes = "application/json")
	public User userAndPass(@RequestBody UserLoginDTO user) {
		return  userService.SelByUsernameAndPassword(user);
	}
	
	
	
	@GetMapping(value = "/username/{userName}", produces = "application/json")
	public User getById(@PathVariable("userName") String userName) {
		return  userService.SelByUserName(userName);
	}
	

//	
//	@GetMapping(value = "/user/{idUser}/addFavorite/{idNFT}")
//	public ResponseEntity<User> addFavorites(@PathVariable("idUser")String idUser,@PathVariable("idNFT")String idNFT)
//	{
//			
//			userService.addFav(idUser, idNFT);
//			
//			return new ResponseEntity<User>(new HttpHeaders(), HttpStatus.CREATED);  
//	}
}
