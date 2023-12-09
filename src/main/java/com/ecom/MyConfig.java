package com.ecom;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecom.deposit.Deposit;
import com.ecom.deposit.DepositRepository;
import com.ecom.user.UserAccount;
import com.ecom.user.UserRepository;

@Component
public class MyConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DepositRepository depositRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		UserAccount user = new UserAccount();
		user.setFirstName("MyConfig");
		user.setLastName("User_01");
        //userRepository.save(user); 
        
		UserAccount user2 = new UserAccount();
		user2.setFirstName("MyConfig2");
		user2.setLastName("User_02");
		
		UserAccount user3 = new UserAccount();
		user3.setFirstName("MyConfig3");
		user3.setLastName("User_03");
		
		userRepository.saveAll(List.of(user, user2, user3)); 
       
		Deposit frst = new Deposit(new Date(), 44444L, true, user3);
		//date, Long depositValue, boolean authorized, UserAccount userAccount

        Deposit deposit = new Deposit();
        deposit.setDepositValue(1234L);
        deposit.setUserAccount(user); 
        
        Deposit deposit2 = new Deposit();
        deposit2.setDepositValue(-1234L);
        deposit2.setUserAccount(user);
        
        Deposit deposit3 = new Deposit();
        deposit3.setUserAccount(user2);
        
        Deposit deposit4 = new Deposit();
        deposit4.setDepositValue(999L);
        deposit4.setUserAccount(user);
        
        Deposit deposit5 = new Deposit();
        deposit5.setDepositValue(22L);
        deposit5.setUserAccount(user3);
        

        depositRepository.saveAll(List.of(frst, deposit, deposit2, deposit3, deposit4, deposit5));
		
	}
	
	

}
