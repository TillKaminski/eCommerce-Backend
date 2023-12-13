package com.ecom.deposit;
//import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/*
 * 	Verarbeitet Anfragen für Zahlungen
 * 
 * 	URLs: api
 * 	"/{userId}/payments" 
 * 	"/{userId}/paymentssorted/[Order]"
 * 	"/payments/sum/[Begin]/[End]"
 * 	"/payments/summissing/[Begin]/[End]"
 * 	"/payments/[Begin]/[End]"
 */




@RequestMapping(path = "api")
@RestController
public class DepositController {
	
	private final DepositService depositService;
	
	//Wrapper
	/*
	private final class PeriodBody {
		@JsonProperty("dateBegin")
		private String dateBegin;
		@JsonProperty("dateEnd")
		private String dateEnd;
		
		public String getDateBegin() {
			return dateBegin;
		}

		public String getDateEnd() {
			return dateEnd;
		}

		public PeriodBody() {}
	
	}
	*/
	
	public DepositController(DepositService depositService) {
		this.depositService =  depositService;
	}

	@GetMapping (path = "/{userId}/payments")
	public ResponseEntity<List<Deposit>> getPayments(@PathVariable("userId") Long userId) {
		List<Deposit> deposit = depositService.getDepositByUserId(userId);
		return new ResponseEntity<>(deposit, HttpStatus.OK );
	}
	
	@GetMapping (path = "/{userId}/paymentssorted/{boolOrder}")
	public ResponseEntity<List<Deposit>> getPaymentsSorted(@PathVariable("userId") Long userId, @PathVariable("boolOrder") String stringOrder) {
		Boolean boolOrder = (stringOrder == "up") ? true : false; // == "down" abfragen?!
		//System.out.println(stringOrder);
		List<Deposit> deposit = depositService.getDepositByUserIdSorted(userId, boolOrder);
		return new ResponseEntity<>(deposit, HttpStatus.OK );
	}
	
	//TODO DO Wrapper für Datum
	@GetMapping (path = "/payments/sum/{dateBegin}/{dateEnd}")
	public ResponseEntity<Long> getPaymentSum(@PathVariable("dateBegin") String strDateBegin, @PathVariable("dateEnd") String strDateEnd) {
		return new ResponseEntity<>(depositService.getDepositSumPeriod(strDateBegin, strDateEnd), HttpStatus.OK);  
	}
	
	@GetMapping (path = "/payments/summissing/{dateBegin}/{dateEnd}")
	public ResponseEntity<Long> getPaymentSumMissing(@PathVariable("dateBegin") String strDateBegin, @PathVariable("dateEnd") String strDateEnd) {
		return new ResponseEntity<>(depositService.getDepositSumMissingPeriod(strDateBegin, strDateEnd), HttpStatus.OK);  
	}
	
	@GetMapping (path = "/payments/{dateBegin}/{dateEnd}")
	public ResponseEntity<List<Deposit>> getPaymentsPeriod(@PathVariable("dateBegin") String strDateBegin, @PathVariable("dateEnd") String strDateEnd) {
		
		return new ResponseEntity<>(depositService.getDepositPeriod(strDateBegin, strDateEnd), HttpStatus.OK);  
	}
	
	// Test
	@GetMapping (path = "/pall")
	public ResponseEntity<List<Deposit>> getAlld() {
		return new ResponseEntity<>(depositService.getPTest(), HttpStatus.OK);  
	}	
}
