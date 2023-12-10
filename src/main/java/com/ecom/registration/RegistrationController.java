package com.ecom.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.UserAccount;

/*
 * 	Registrierung und Login
 * 
 *	Registriert Benutzer, Gibt Benutzer mit Benutzer.Role zurück
 *	wenn erfolglos -> leerer Benutzer mit UserRole.DENIED 
 *
 *	Login gibt Nutzerrolle als String zurück
 *	falls Passwort falsch oder Benutzer nicht vorhanden wird UserRole.DENIED ausgegeben
 *
 *	URLs: api/user
 *
 *	"/register"
 *	"/login"
 */


@RestController
@RequestMapping(path = "api/user")
public class RegistrationController {
	
	//DI
	private final RegistrationService registrationService;
	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@PostMapping(path =  "/register")
	public ResponseEntity<UserAccount> registerUser(@RequestBody UserAccount userAccount) {
		return new ResponseEntity<>(registrationService.registerUser(userAccount), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/login")
	public ResponseEntity<String> loginUser(@RequestBody UserAccount userAccount) {
		return new ResponseEntity<>(registrationService.loginUser(userAccount), HttpStatus.OK);
	}
	

}
