package com.ecom.deposit;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.UserAccount;

/*
 * 	Verarbeitet Anfragen für Zahlungen
 * 
 * 	URL: api
 * 	"/{userId}/payments" 
 * 	"/{userId}/paymentssorted/{boolOrder}"
 * 	"/payments/sumperiod/{dateBegin}/{dateEnd}"
 * 	"/addpayment"
 * 	"/resubpayment"
 */




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
		
		LocalDate testDate = LocalDate.now();
		System.out.println("€€€Date" + testDate);
		return new ResponseEntity<>(deposit, HttpStatus.OK );
	}
	
	@GetMapping (path = "/{userId}/paymentssorted/{boolOrder}")
	public ResponseEntity<List<Deposit>> getPaymentsSorted(@PathVariable("userId") Long userId, @PathVariable("boolOrder") Boolean boolOrder) {
		List<Deposit> deposit = depositService.getDepositByUserIdSorted(userId, boolOrder);
		//System.out.println("€€€DepoContr" + userId);
		return new ResponseEntity<>(deposit, HttpStatus.OK );
	}
	
	@GetMapping (path = "/payments/sumperiod/{dateBegin}/{dateEnd}")
	public ResponseEntity<Long> getPaymentSum(@PathVariable("dateBegin") String strDateBegin, @PathVariable("dateEnd") String strDateEnd) {
		System.out.println("String " + strDateBegin);
		System.out.println("String " + strDateEnd);
		return new ResponseEntity<>(depositService.getDepositSumPeriod(strDateBegin, strDateEnd), HttpStatus.OK);  
	}
	
	
	@PostMapping (path = "/addpayment")
	// TODO ? PostMapping nicht notwendig, da Mapping in PaymentController. Zwei Bodies nicht möglich
	public ResponseEntity<Deposit> addDeposit(@RequestBody UserAccount userAccount, @RequestBody Deposit deposit) {
		Deposit tmpDeposit = depositService.addDeposit(userAccount, deposit);
		return new ResponseEntity<>(tmpDeposit, HttpStatus.OK);
	}
	
	@PutMapping (path = "/resubpayment")
	// TODO DO nur für Mitarbeiter
	public ResponseEntity<Deposit> resubmitDeposit(@RequestBody UserAccount userAccount, @RequestBody Deposit deposit) {
		Deposit tmpDeposit = depositService.addDeposit(userAccount, deposit);
		return new ResponseEntity<>(tmpDeposit, HttpStatus.OK);
	}
	
	
}
