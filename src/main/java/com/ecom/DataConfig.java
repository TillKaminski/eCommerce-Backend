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


/**
 * Erstellung von Dummy-Daten
 */
@Component
public class DataConfig implements CommandLineRunner {

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
	}
}
