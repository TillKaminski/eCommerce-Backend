package com.ecom.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/register")
public class RegistraionController {
	
	//DI
	private final RegistraionService registraionService;
	
	public RegistraionController(RegistraionService registraionService) {
		this.registraionService = registraionService;
	}
	
	@GetMapping(path = "test")
	public ResponseEntity<String> registerUser() {
		registraionService.test();
		return new ResponseEntity<>("TODO!! Create", HttpStatus.CREATED);
	}

}
