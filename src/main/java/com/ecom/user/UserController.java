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

@RestController
@RequestMapping(path = "api")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping (path = "/all")
	public ResponseEntity <List<UserAccount>> getUsers() {
		List<UserAccount> users = userService.getUserAll();
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
	


}
