package com.ecom.deposit;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.UserAccount;

@RequestMapping(path = "api")
@RestController
public class DepositController {
	
	private final DepositService depositService;
	
	public DepositController(DepositService depositService) {
		this.depositService =  depositService;
	}

	@GetMapping (path = "/{userId}/payments")
	public ResponseEntity<List<Deposit>> getPayments(@PathVariable("userId") Long userId) {
		List<Deposit> deposit = depositService.getDepositByUserId(userId);
		System.out.println("€€€DepoContr" + userId);
		return new ResponseEntity<>(deposit, HttpStatus.OK );
	}
	
	@PutMapping (path = "/addpayment")
	public ResponseEntity<Deposit> addDeposit(@RequestBody UserAccount userAccount, @RequestBody Deposit deposit) {
		Deposit tmpDeposit = depositService.addDeposit(deposit, userAccount);
		return new ResponseEntity<>(tmpDeposit, HttpStatus.OK);
	}
}
