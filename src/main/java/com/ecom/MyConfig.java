package com.ecom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecom.payment.Deposit;
import com.ecom.payment.DepositRepository;
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
        //userRepository.save(user); 
        
		UserAccount user2 = new UserAccount();
		user2.setFirstName("MyConfig2");
       userRepository.saveAll(List.of(user, user2)); 

        Deposit deposit = new Deposit();
        deposit.setUserAccount(user); 
        
        Deposit deposit2 = new Deposit();
        deposit2.setUserAccount(user);
        
        Deposit deposit3 = new Deposit();
        deposit3.setUserAccount(user2);

        depositRepository.saveAll(List.of(deposit, deposit2, deposit3));
		
	}
	
	

}
