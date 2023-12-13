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
import com.ecom.user.UserRole;


/*
 * Erstellung von Dummy-Daten
 */

@Component
public class UserDepoConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DepositRepository depositRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		UserAccount user1 = new UserAccount("Ruth ", "Stamer", "ruth@stamer.de", 123456L, UserRole.USER);;
		UserAccount user2 = new UserAccount("Annette", "Hänsel", "annette@haensel.de", 0L, UserRole.USER);		
		UserAccount user3 = new UserAccount("Karl", "Baierl", "karl@baierl.de", -123456L, UserRole.USER);
		user1.setNumberTransactions(4L);
		user2.setNumberTransactions(4L);
		user3.setNumberTransactions(4L);
		userRepository.saveAll(List.of(user1, user2, user3));
		
		Deposit dep1 = new Deposit(LocalDate.parse("2010-01-01"), -1000L, "Abhebung 10€", true, user1);
		Deposit dep2 = new Deposit(LocalDate.parse("2011-01-01"), 1230L, "Test 12,30€", true, user1);
		Deposit dep3 = new Deposit(LocalDate.parse("2023-12-05"), 2000L, "Austehend", false, user1);
		Deposit dep4 = new Deposit(LocalDate.parse("2023-12-06"), 11111L, "Test 111,1€", true, user1);
		
		Deposit dep5 = new Deposit(LocalDate.parse("2011-01-01"), 1000L, "Test Ausstehend", false, user2);
		Deposit dep6 = new Deposit(LocalDate.parse("2012-01-01"), 1000L, "Einzahlung", true, user2);
		Deposit dep7 = new Deposit(LocalDate.parse("2012-02-01"), 1000L, "Beschreibung", true, user2);
		Deposit dep8 = new Deposit(LocalDate.parse("2020-01-01"), 1000L, "Einzahlung", true, user2);
		
		Deposit dep9 = new Deposit(LocalDate.parse("2022-01-01"), -1000L, "Abhebung durchgeführt", true, user3);
		Deposit dep10 = new Deposit(LocalDate.parse("2010-07-01"), 2222L, "Einzahlung", false, user3);
		Deposit dep11 = new Deposit(LocalDate.parse("2015-01-01"), 40L, "Test 0,40€", false, user3);
		Deposit dep12 = new Deposit(LocalDate.parse("2010-01-01"), 1000L, "Test 10€", true, user3);
		
		depositRepository.saveAll(List.of(dep1, dep2, dep3, dep4, dep5, dep6, dep7, dep8, dep9, dep10, dep11, dep12));
		/*
		UserAccount user = new UserAccount();
		user.setFirstName("MyConfig");
		user.setLastName("User_01");
		user.setBalance(123456L);
		user.setNumberTransactions(1L);
		user.setEmail("Test");
		user.setPassword("Test");
		user.setUserRole(UserRole.USER);
        //userRepository.save(user); 
        
		UserAccount user2 = new UserAccount();
		user2.setEmail("Mail2");
		user2.setFirstName("MyConfig2");
		user2.setLastName("User_02");
		user2.setBalance(-321L);
		user2.setNumberTransactions(1L);;
		user2.setPassword("EMP");
		user2.setUserRole(UserRole.EMPLOYEE);
		
		UserAccount user3 = new UserAccount();
		user3.setEmail("Mail3");
		user3.setFirstName("MyConfig3");
		user3.setLastName("User_03");
		user3.setBalance(5L);
		user3.setNumberTransactions(1L);
		
		UserAccount user4 = new UserAccount();
		user4.setEmail("Mail4");
		user4.setFirstName("H2_neu");
		user4.setLastName("H2_neu");
		user4.setBalance(555L);
		
		userRepository.saveAll(List.of(user, user2, user3, user4)); 
       
		LocalDate nowDate = LocalDate.now();  // TODO DO LocalDate ausreichend?!
		
		Deposit frst = new Deposit(LocalDate.now(), 44444L, "Test myConfig", true, user3);
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
		*/
	
	}
	
	

}
