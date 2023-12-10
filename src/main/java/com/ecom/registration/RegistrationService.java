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
public class RegistrationService {
	
	private final RegistrationRepository registrationRepository;
	
	// DI
	public RegistrationService(RegistrationRepository registrationRepository) {
		this.registrationRepository = registrationRepository;
	}
	
	private String hashPassword(String password) {
		// TODO DO verschlüsseln!
		return "hash123" + password + "456hash";
	}
	
	public UserAccount registerUser(UserAccount userAccount) {
		
		UserAccount checkUser = registrationRepository.findUserAccountByEMail(userAccount.geteMail()).get();
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
	
	public String loginUser(UserAccount userAccount) {
		
		UserRole loginRole;
		
		// Exceptions
		try {
			UserAccount loginAccount = registrationRepository.findById(userAccount.getId()).get();
			if (loginAccount.getPassword() == hashPassword(userAccount.getPassword())) {
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

	/*
	private long id;
	private String firstName;
	private String lastName;
	private String eMail;
	private long balance;
	private UserRole userRole;
	private String password;

	 */
	
	
	

}
