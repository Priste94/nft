package com.app.NFT.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.NFT.dto.UserDTO;
import com.app.NFT.dto.UserLoginDTO;
import com.app.NFT.entities.NFT;
import com.app.NFT.entities.Transaction;
import com.app.NFT.entities.User;
import com.app.NFT.repository.NFTRepository;
import com.app.NFT.repository.UserRepository;



@Service
@Transactional
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private ModelMapper modelMapper;
	
//	@Autowired
//	private NFTService nFTService;


	@Override
	public List<User> SelAll() {
		return userRepository.findAll();
	}
	
	
	public List<UserDTO> SelAllDTO() {
		return userRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	 private UserDTO convertEntityToDto(User user){
	        modelMapper.getConfiguration()
	                .setMatchingStrategy(MatchingStrategies.LOOSE);
	        UserDTO userDTO = new UserDTO();
	        userDTO = modelMapper.map(user, UserDTO.class);
	        return userDTO;
	    }
	 
	 private UserLoginDTO convertEntityToDto2(User user){
	        modelMapper.getConfiguration()
	                .setMatchingStrategy(MatchingStrategies.LOOSE);
	        UserLoginDTO userLoginDTO = new UserLoginDTO();
	        userLoginDTO = modelMapper.map(user, UserLoginDTO.class);
	        return userLoginDTO;
	    }


	@Override
	public User SelByIdUser(int IdUser) {
		return userRepository.findByIdu(IdUser);
	}

	@Override
	public ResponseEntity<User> InsUser(User user) {
		if (user==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			if(user.getName()!=null && user.getUserName()!=null && user.getPassword()!=null && isUniqueUserName(user) && user.getIdu() ==  0) {
				User u = SelByUserName(user.getUserName());
				if (user.getIdu() ==  0 && u==null) {
					   		
						userRepository.save(user);
						
						return new ResponseEntity<User>(new HttpHeaders(), HttpStatus.CREATED);  
			//
					}
					}	
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
	
		
	}
	
	
	public boolean isUniqueUserName(User user) {		
		if (user!=null) {
			User us;
		    us = userRepository.findByUserName(user.getUserName());
			if(us == null || user.getUserName()==null) 
				return true;
			return false;
		}
		
			return true;

	}

	@Override
	public void DelUser(User user) {
		userRepository.delete(user);
		
	}

	

	public User SelByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}


	public User SelByUsernameAndPassword(UserLoginDTO user) {
		return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		
	}
	
	
//	public void addFav(String idu, String idn) {
//		
//		User u = SelByIdUser(Integer.parseInt(idu));
//		
//		NFT n = nFTService.SelByIdNFT(Integer.parseInt(idn));
//		
//		System.out.println(u.getIdu());
//		System.out.println(n.getIdn());
//		u.getFavorites().add(n);
//		//n.getUsers().add(u);
//		//userRepository.save(u);
//		
//	}

	
	




}
