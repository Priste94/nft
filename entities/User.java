package com.app.NFT.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idu;
     
    @Column(name = "username", nullable = false, unique = true, length = 45)
    @NotBlank(message = "This field is required")
	@Length(min = 2, message = "Min length is 2")
    private String userName;
     
    @Column(name = "password",nullable = false, length = 64)
    @NotBlank(message = "This field is required")
	@Length(min = 6, message = "Min length is 6")
    private String password;
     
}
