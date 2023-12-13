package com.ecom.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.UserAccount;
import com.fasterxml.jackson.annotation.JsonProperty;

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
 *	"/register"
 *	"/login/[userMail]"
 *	
 */


@RestController
@RequestMapping(path = "api/user")
public class RegistController {
	
	//DI
	private final RegistService registrationService;
	
	//Wrapper
	public static class LoginBody{
		@JsonProperty("email")
		private String email;
		@JsonProperty("password")
		private String password;
		
		/*
		public LoginBody(String email, String password) {
			super();
			this.email = email;
			this.password = password;
		}
		*/

		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}		
	}
	
	public RegistController(RegistService registrationService) {
		this.registrationService = registrationService;
	}
	
	@PostMapping(path =  "/register")
	public ResponseEntity<UserAccount> registerUser(@RequestBody UserAccount userAccount) {
		System.out.println("###123 " + userAccount);
		return new ResponseEntity<>(registrationService.registerUser(userAccount), HttpStatus.CREATED);
	}
		
	// TODO ? POST für RequestBody
	// Mail + PW = UserId;
	@PostMapping(path = "/login/{userMail}")
	public ResponseEntity<Long> loginUser(@PathVariable("userMail") String userMail, @RequestBody String userPassword) {
		System.out.println("xxx!!" + userMail + userPassword);
		return new ResponseEntity<>(registrationService.loginUser(userMail, userPassword), HttpStatus.OK);
	}
	
	@PostMapping(path = "/get")
	public ResponseEntity<UserAccount> getUser(@RequestBody LoginBody loginBody) {
		return new ResponseEntity<>(registrationService.getUser(loginBody.getEmail()), HttpStatus.OK);
	}
	

	
	
	

}
