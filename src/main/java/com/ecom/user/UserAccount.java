package com.ecom.user;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserAccount {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private long id;
	private String firstName;
	private String lastName;
	private String eMail;
	private long balance;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
}
