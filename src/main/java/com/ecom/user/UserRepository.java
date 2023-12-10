package com.ecom.user;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

/*
 * 	Stellt Standard CRUD-Operationen bereit 
 * 	Erweiterung möglich für problemspezifische Datenbankzugriffe
 * 
 */


public interface UserRepository extends JpaRepository<UserAccount, Long> {
	
	// @Query("SELECT * FROM UserAccount LEFT JOIN Deposit WHERE Deposit.user_account_id = :userAccountId")
	// UserAccount findIdWithDeposit(@Param("userAccountId") Long userAccountId);

}
