package com.ecom.registration;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.user.UserAccount;

public interface RegistraionRepository extends JpaRepository<UserAccount, Long> {

}
