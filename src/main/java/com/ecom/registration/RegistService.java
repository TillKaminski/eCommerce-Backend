package com.ecom.registration;



//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ecom.user.UserAccount;
import com.ecom.user.UserRole;

/*
 * 
 * 	Legt neue Benutzerkonten an
 * 	Auch für Login verwendet
 * 
 */

@Service
public class RegistService {
	
	private final RegistRepository registrationRepository;
	
	// DI
	public RegistService(RegistRepository registrationRepository) {
		this.registrationRepository = registrationRepository;
	}
	
	private String hashPassword(String password) {
		// TODO DO verschlüsseln!
		return "hash123" + password + "456hash";
	}
	
	public UserAccount registerUser(UserAccount userAccount) {
		
		UserAccount checkUser = registrationRepository.findUserAccountByEmail(userAccount.geteMail()).get();
		if (checkUser == null) {
			UserAccount registerAccount = new UserAccount(userAccount.getFirstName(), userAccount.getLastName(),
					userAccount.geteMail(), userAccount.getBalance(), userAccount.getUserRole());
			// TODO PW wird unverschlüsselt ans BE übertragen?! Problem FE?!
			registerAccount.setPassword(hashPassword(userAccount.getPassword()));
			return registrationRepository.save(registerAccount);
		} else {
			System.out.println("Email vergeben!");
			UserAccount registerAccount = new UserAccount();
			registerAccount.setUserRole(UserRole.DENIED);
			return registerAccount;
		}
	}
	
	public UserAccount getUser(String email) {
		UserAccount userByEmail = registrationRepository.findUserAccountByEmail(email).get();
		return userByEmail;
	}
	
	public String loginUser(Long userAccountId, String userAccountPassword) {
		
		UserRole loginRole;
		
		// Exceptions
		try {
			UserAccount loginAccount = registrationRepository.findById(userAccountId).get();
			if (loginAccount.getPassword() == hashPassword(userAccountPassword)) {
				loginRole = loginAccount.getUserRole();	
			} else {
				loginRole = UserRole.DENIED;
			}
				
		} catch (Exception e) {
			loginRole = UserRole.DENIED;
			System.out.println("Login fehlgeschlagen!");
			System.out.println("Error: " + e.getMessage());
		}
		return loginRole.toString();	
	}

}
