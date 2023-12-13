package com.ecom.payment;

import org.springframework.stereotype.Service;

import com.ecom.deposit.Deposit;
import com.ecom.deposit.DepositService;
import com.ecom.user.UserAccount;
import com.ecom.user.UserService;


/*
 * 	Reicht Zahlungsanfragen weiter
 * 	Initiiert Verarbeitung von Zahlung auf Kunden und Zahlungsebene
 */

@Service
public class PaymentService {
	private final UserService userService;
	private final DepositService depositService;
	
	public PaymentService(UserService userService, DepositService depositService) {
		this.userService = userService;
		this.depositService = depositService;
	}
	
	public UserAccount verifyUser(Long UserId) {
		if (userService.getUserById(UserId) != null) {
			return userService.getUserById(UserId);
		}
		return null;
	}
	
	public boolean addDeposit(UserAccount userAccount, Deposit deposit) {
		Deposit tmpDeposit = depositService.addDeposit(userAccount, deposit); 	//Kunden mit Zahlung verknüpfen
		return userService.addDeposit(userAccount, tmpDeposit);					//Kundendaten updaten
	}
	
	public boolean resubmitDeposit(UserAccount userAccount, Deposit deposit) {
		this.depositService.resubmitDeposit(userAccount, deposit); 	
		return this.userService.resubmitDeposit(userAccount, deposit);			//Erneute Zahlung, Anzahl Transaktionen unverändert
	}
		
	
	
}
