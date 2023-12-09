package com.ecom.payment;

import org.springframework.stereotype.Service;

import com.ecom.deposit.Deposit;
import com.ecom.deposit.DepositService;
import com.ecom.user.UserAccount;
import com.ecom.user.UserService;

@Service
public class PaymentService {
	private final UserService userService;
	private final DepositService depositService;
	
	public PaymentService(UserService userService, DepositService depositService) {
		this.userService = userService;
		this.depositService = depositService;
	}
	
	public boolean addDeposit(Deposit deposit, UserAccount userAccount) {
		Deposit tmpDeposit = depositService.addDeposit(deposit, userAccount); 	//Kunden mit Zahlung verknüpfen
		return userService.addDeposit(userAccount, tmpDeposit);					//Kundendaten updaten
	}
}
