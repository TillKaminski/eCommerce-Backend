package com.ecom.user;

//import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfig {
	
	// TODO Überflüssig, da gemeinsame MyConfig
	
	@Bean
	CommandLineRunner commandLineRunnerUser (UserRepository userRepository) {
		return args -> {
			//UserAccount user1 = new UserAccount();
			//UserAccount user2 = new UserAccount();
			//user2.setLastName("Test LastName");
			//UserAccount user3 = new UserAccount("TestfirstName", "Test String lastName", "Test String eMail", 2000L, UserRole.ADMIN);
			
			//userRepository.saveAll(List.of(user1, user2));
			
			
		};
		
	}

}
