package com.ecom.deposit;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.user.UserAccount;

@Service
public class DepositService {

	private final DepositRepository depositRepository;
	
	public DepositService(DepositRepository depositRepository) {
		this.depositRepository = depositRepository;
	}	


	public List<Deposit> getDepositByUserId(Long userId) {
		// TODO Anpassen: zu userId Zahlungen finden
		//Deposit test11 = depositRepository.fin
		return depositRepository.findDepositByUserAccountId(userId).get();
	}
	
	public Deposit addDeposit(Deposit deposit, UserAccount userAccount) {
		if (deposit.getDepositValue() > 0L) {deposit.setAuthorized(true);}; // TODO Zahlungen > 0 immer moeglich?! eventuell anpassen
		deposit.setUserAccount(userAccount);
		this.depositRepository.save(deposit);
		return deposit;
	}
	
	
}
