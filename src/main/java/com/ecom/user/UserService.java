package com.ecom.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.deposit.Deposit;

@Service
public class UserService {

	//Dependency Injection
	private final UserRepository userRepository;
	
	// TODO Business Logic
	
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserAccount> getUserAll() {
		return userRepository.findAll();
	}
	
	

	public UserAccount editUser(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createUser(UserAccount userAccount) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUserByID(Long userID) {
		// TODO Auto-generated method stub
		
	}

	public boolean addDeposit(UserAccount userAccount, Deposit deposit) {
		// TODO Zahlungen > 0 ?! anpassen
		if (deposit.getDepositValue() > 0L || deposit.isAuthorized()) {
			Long editBalance = userAccount.getBalance() + deposit.getDepositValue();
			userAccount.setBalance(editBalance);
			return true;
		}
		return false;
		
	}


	
}
