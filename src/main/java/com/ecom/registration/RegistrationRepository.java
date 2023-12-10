package com.ecom.registration;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.user.UserAccount;


// sinnvoll? entspricht UserRepo
public interface RegistrationRepository extends JpaRepository<UserAccount, Long> {
	Optional<UserAccount> findUserAccountByEMail(String userAccountEMail);
}
