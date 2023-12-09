package com.ecom;

import java.time.LocalDate;
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
		user.setBalance(123456L);
        //userRepository.save(user); 
        
		UserAccount user2 = new UserAccount();
		user2.setFirstName("MyConfig2");
		user2.setLastName("User_02");
		user2.setBalance(-321L);
		
		UserAccount user3 = new UserAccount();
		user3.setFirstName("MyConfig3");
		user3.setLastName("User_03");
		user3.setBalance(5L);
		
		userRepository.saveAll(List.of(user, user2, user3)); 
       
		LocalDate nowDate = LocalDate.now();  // TODO Date -> LocalDate
		
		Deposit frst = new Deposit(LocalDate.now(), 44444L, true, user3);
		//date, Long depositValue, boolean authorized, UserAccount userAccount

	
        Deposit deposit = new Deposit();
        deposit.setDate(nowDate);
        deposit.setDepositValue(1234L);
        deposit.setUserAccount(user); 
        
        LocalDate nowDate1 = LocalDate.of(2023, 5, 5);
        Deposit deposit2 = new Deposit();
        deposit2.setDate(nowDate1);
        deposit2.setDepositValue(-1234L);
        deposit2.setUserAccount(user);
        
        LocalDate nowDate2 = LocalDate.of(2023, 4, 5);
        Deposit deposit3 = new Deposit();
        deposit3.setDate(nowDate2);
        deposit3.setUserAccount(user2);
        
        LocalDate nowDate3 = LocalDate.of(2021, 5, 5);
        Deposit deposit4 = new Deposit();
        deposit4.setDate(nowDate3);
        deposit4.setDepositValue(999L);
        deposit4.setUserAccount(user);
        
        LocalDate nowDate4 = LocalDate.of(2023, 5, 6);
        Deposit deposit5 = new Deposit();
        deposit5.setDate(nowDate4);
        deposit5.setDepositValue(22L);
        deposit5.setUserAccount(user3);
        
        LocalDate nowDate5 = LocalDate.of(2022, 5, 6);
        Deposit deposit6 = new Deposit();
        deposit6.setDate(nowDate5);
        deposit6.setDepositValue(1234L);
        deposit6.setUserAccount(user);
        

        depositRepository.saveAll(List.of(frst, deposit, deposit2, deposit3, deposit4, deposit5, deposit6));
		
	}
	
	

}
