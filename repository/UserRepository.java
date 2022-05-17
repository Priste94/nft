package com.app.NFT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.NFT.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    public User findByUserName(String userName);
     
}
