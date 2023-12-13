package com.ecom.registration;



//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ecom.user.UserAccount;
//import com.ecom.user.UserRole;

/*
 * 	Legt neue Benutzerkonten an
 * 	Auch für Login verwendet
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
		
		// TODO prüfen ob Mail mehrfach vergeben
		//UserAccount checkUser = registrationRepository.findUserAccountByEmail(userAccount.getEmail()).get();
		//if (checkUser == null) {
			UserAccount registerAccount = new UserAccount(userAccount.getFirstName(), userAccount.getLastName(),
					userAccount.getEmail(), userAccount.getBalance(), userAccount.getUserRole());
			// TODO PW wird unverschlüsselt ans BE übertragen?! Problem FE?!
			registerAccount.setPassword(hashPassword(userAccount.getPassword()));
			return registrationRepository.save(registerAccount);
		//} else {
		//	System.out.println("Email vergeben!");
		//	UserAccount registerAccount = new UserAccount();
		//	registerAccount.setUserRole(UserRole.DENIED);
		//	return registerAccount;
		//}
	}
	
	public UserAccount getUser(String email) {
		UserAccount userByEmail = registrationRepository.findUserAccountByEmail(email).get();
		return userByEmail;
	}
	
	public long loginUser(String email, String userAccountPassword) {
		
		System.out.println("Login");
		UserAccount loginAccount = registrationRepository.findUserAccountByEmail(email).get();
		
		System.out.println("Loginx");
		System.out.println(loginAccount.getPassword());
		System.out.println(userAccountPassword);
		
		if(loginAccount.getPassword().trim() == userAccountPassword.trim()) {
			System.out.println("Loginxddd");
			System.out.println(loginAccount.getId());
			return loginAccount.getId();
		}
		return 1L;
		
		/*
		UserRole loginRole = UserRole.DENIED;
		UserAccount loginAccount = new UserAccount();
		// Exceptions
		try {
			loginAccount = registrationRepository.findUserAccountByEmail(email).get();
			if (loginAccount.getPassword() == hashPassword(userAccountPassword)) {
				loginRole = loginAccount.getUserRole();	
			} else {
				loginRole = UserRole.DENIED;
			}
				
		} catch (Exception e) {
			if (loginRole == UserRole.DENIED); {
			System.out.println("Login fehlgeschlagen!");
			System.out.println("Error: " + e.getMessage());
			}
		}
		
		return loginAccount.getId();
		*/	
	}

}
