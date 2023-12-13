package com.ecom.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.deposit.Deposit;

/*
 * 	Nimmt Anfragen vom Controller entgegen und verarbeite diese
 * 
 * 	Standardoperationen aus JPA-Repo Interface
 *
 * 	Business Logic wird an dieser Stelle implementiert
 */

@Service
public class UserService {

	//Dependency Injection
	private final UserRepository userRepository;	
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserAccount> getUserAll() {
		return userRepository.findAll();
	}
	
	public List<UserAccount> getUserSorted() {
		List<UserAccount> userAccount = userRepository.findAll();
		userAccount.sort(UserAccount.balanceComparator);
		return userAccount;
	}
	
	public List<UserAccount> getUserSortedRev() {
		List<UserAccount> userAccount = userRepository.findAll();
		userAccount.sort(UserAccount.balanceComparatorRev);
		return userAccount;
	}
	
	public UserAccount getUserById(Long userId) {
		if (userRepository.existsById(userId)) {
			return userRepository.findById(userId).get();
		} 
		return null;
	}
	
	// Kein UserManagement!
	public UserAccount editUser(UserAccount userAccount) {
		// TODO DEL public UserAccount editUser(UserAccount userAccount)
		return null;
	}

	public void createUser(UserAccount userAccount) {
		// TODO DEL public void createUser(UserAccount userAccount)
		// ausgelagert in Registration	
	}

	public void deleteUserByID(Long userID) {
		// TODO DO public void deleteUserByID(Long userID)
	}

	public boolean addDeposit(UserAccount userAccount, Deposit deposit) {
		// TODO ? Zahlungen > 0 ?! anpassen, Gebühren etc
		if (deposit.getDepositValue() >= 0L || deposit.isAuthorized()) {
			Long editBalance = userAccount.getBalance() + deposit.getDepositValue();
			userAccount.setBalance(editBalance);
			userAccount.setNumberTransactions(userAccount.getNumberTransactions() + 1);
			userRepository.save(userAccount);
			return true;
		}
		return false;		
	}

	public boolean resubmitDeposit(UserAccount userAccount, Deposit deposit) {
		// TODO ? Zahlungen > 0 ?! anpassen, Gebühren etc
		//System.out.println("TTT" + userAccount.getDeposit());
			Long editBalance = userAccount.getBalance() + deposit.getDepositValue();
			userAccount.setBalance(editBalance);
			userRepository.save(userAccount);
			System.out.println(userAccount.getDeposit());
			return true;	
	}
}
