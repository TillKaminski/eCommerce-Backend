package com.ecom.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.deposit.Deposit;

/*
 * 	
 * 	Nimmt Anfragen vom Controller entgegen und verarbeite diese
 * 
 * 	Standardoperationen aus JPA-Repo Interface
 *
 * 	Business Logic wird an dieser Stelle implementiert
 * 
 * 
 */



@Service
public class UserService {

	//Dependency Injection
	private final UserRepository userRepository;
	
	// TODO Business Logic
	
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	// TODO Sortierung nach Kontostand (flexibel durch Feld?!)
	public List<UserAccount> getUserAll() {
		return userRepository.findAll();
	}
	
	public List<UserAccount> getUserSorted() {
		List<UserAccount> userAccount = userRepository.findAll();
		userAccount.sort(UserAccount.balanceComparator);
		return userAccount;
	}
	
	public UserAccount getUserById(Long userId) {
		if (userRepository.existsById(userId)) {
			return userRepository.findById(userId).get();
		} 
		return null;
	}
	
	

	public UserAccount editUser(UserAccount userAccount) {
		// TODO public UserAccount editUser(UserAccount userAccount)
		return null;
	}

	public void createUser(UserAccount userAccount) {
		// TODO public void createUser(UserAccount userAccount)
		
	}

	public void deleteUserByID(Long userID) {
		// TODO public void deleteUserByID(Long userID)
		
	}

	public boolean addDeposit(UserAccount userAccount, Deposit deposit) {
		// TODO Zahlungen > 0 ?! anpassen, Gebühren etc
		if (deposit.getDepositValue() > 0L || deposit.isAuthorized()) {
			Long editBalance = userAccount.getBalance() + deposit.getDepositValue();
			userAccount.setBalance(editBalance);
			return true;
		}
		return false;
		
	}


	
}
