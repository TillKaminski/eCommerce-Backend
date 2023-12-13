package com.ecom.registration;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.user.UserAccount;


// sinnvoll? entspricht UserRepo
public interface RegistRepository extends JpaRepository<UserAccount, Long> {
	Optional<UserAccount> findUserAccountByEmail(String userAccountEmail);
}
