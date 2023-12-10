package com.ecom.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.ecom.deposit.Deposit;

/*
 * 
 * 	Controller um HTTP Requests an Service weiterzureichen
 * 	nur für Benutzerkonten zuständig
 * 
 * 	URLs:
 *	"/all"
 * 	"/allsorted"
 * 	"/edit"
 * 	"/create"
 * 	"/delete/[ID]"
 * 
 * 
 */



@RestController
@RequestMapping(path = "api")
public class UserController {
	
	
	//Dependency Injection
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	//
	@GetMapping (path = "/all")
	public ResponseEntity <List<UserAccount>> getUsers() {
		List<UserAccount> users = userService.getUserAll();
		System.out.println("€€€UserContr" + users);
		/*
			for (int i = 0; i < users.size(); i++) {
				UserAccount tempAcc = new UserAccount();
				tempAcc =  users.get(i);
				
				System.out.println(tempAcc);
			}
		*/		
		return new ResponseEntity<> (users, HttpStatus.OK);
	}
	
	@GetMapping (path = "/allsorted")
	public ResponseEntity <List<UserAccount>> getUsersSorted() {
		System.out.println("€€€SORT");
		List<UserAccount> users = userService.getUserSorted();		
		return new ResponseEntity<> (users, HttpStatus.OK);
	}
	
	@PutMapping (path = "/edit")
	public ResponseEntity<UserAccount> editUser(@RequestBody UserAccount userAccount) {
		userService.editUser(userAccount);
		return new ResponseEntity<>(userAccount, HttpStatus.OK);	
	}
	
	@PostMapping (path = "/create")
	public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount userAccount) {
		userService.createUser(userAccount);
		return new ResponseEntity<>(userAccount, HttpStatus.OK);
	}
	
	@DeleteMapping (path = "/delete/{userID}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long userID) {
		userService.deleteUserByID(userID);
		return new ResponseEntity<>("Deleted User with ID: " + userID, HttpStatus.OK);
	}
	
	
	/*
	// TODO --- Zahlungen behandeln ---
	@PutMapping (path = "/edit/deposit")
	// TODO PutMapping nicht notwendig, da Mapping in PaymentController. Zwei Bodies nicht möglich
	public ResponseEntity<Boolean> addDeposit(@RequestBody UserAccount userAccount, @RequestBody Deposit deposit) {
		return new ResponseEntity<>(userService.addDeposit(userAccount, deposit), HttpStatus.OK);
	}
	*/
}
