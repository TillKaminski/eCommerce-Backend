package com.ecom.registration;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.user.UserAccount;


@Service
public class RegistraionService {
	
	private final RegistraionRepository registraionRepository;
	
	// DI
	public RegistraionService(RegistraionRepository registraionRepository) {
		this.registraionRepository = registraionRepository;
	}

	public void test() {
		List<UserAccount> userAccount = registraionRepository.findAll();
		userAccount.add(null);
		// TODO Register Service
		
	}
	

	

}
