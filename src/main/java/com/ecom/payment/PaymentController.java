package com.ecom.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.deposit.Deposit;
import com.ecom.user.UserAccount;



/*
 * 	Nimmt Zahlungsanfragen entgegen
 * 
 * 	URLs: api/pay
 * 	"[ID]/addpayment"
 * 	"[ID]/resubpayment"
 */

@RestController
@RequestMapping(path = "api/pay")
public class PaymentController {
	
	private final PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostMapping (path = "{userId}/addpayment")
	// Reicht Zahlung an Kunden und Zahlung weiter
	public ResponseEntity<Boolean> addDeposit(@PathVariable("userId") Long userId, @RequestBody Deposit deposit) {
		// Gehört eigentlich in den Service?!
		if (paymentService.verifyUser(userId) != null) {
			UserAccount userAccount = paymentService.verifyUser(userId);
			Boolean paymentSuccess = paymentService.addDeposit(userAccount, deposit);
			return new ResponseEntity<>(paymentSuccess, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping (path = "{userId}/resubpayment")
	// TODO DO nur für Mitarbeiter, Rolle prüfen!
	public ResponseEntity<Boolean> resubmitDeposit(@PathVariable("userId") Long userId, @RequestBody Deposit deposit) {
		if (paymentService.verifyUser(userId) != null) {
			UserAccount userAccount = paymentService.verifyUser(userId);			
			Boolean paymentSuccess = paymentService.resubmitDeposit(userAccount, deposit);
			return new ResponseEntity<>(paymentSuccess, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}	
}
