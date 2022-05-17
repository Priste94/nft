package com.app.NFT.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import com.app.NFT.entities.Transaction;
import com.app.NFT.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
//	@Query("select new com.app.NFT.entities.User(u.password) from User u")
//	public List<User> trovaTutti();
	
	@Query(value="SELECT * FROM Users", nativeQuery=true)
	public List<User> findAllUsers();
	
    public User findByUserName(String userName);
    public User findByIdu(int id);

	public User findByUserNameAndPassword(String userName, String password);



     
}
