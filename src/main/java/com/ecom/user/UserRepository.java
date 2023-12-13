package com.ecom.user;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * 	Stellt Standard CRUD-Operationen bereit 
 * 	Erweiterung möglich für problemspezifische Datenbankzugriffe
 */


public interface UserRepository extends JpaRepository<UserAccount, Long> {
	
	// @Query("SELECT ua FROM UserAccount ua LEFT JOIN ua.deposits d WHERE d.userAccount.id = :userAccountId")
	// UserAccount findIdWithDeposit(@Param("userAccountId") Long userAccountId);


}
