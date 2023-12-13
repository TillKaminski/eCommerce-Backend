package com.ecom.deposit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 	Zahlungen Dummys
 */


@Configuration
public class DepositConfig {
	
	// TODO DEL Überflüssig, da gemeinsame MyConfig
	
	@Bean
	CommandLineRunner commandLineRunnerDeposit(DepositRepository depositRepositry) {
		return args ->  {
			//Deposit deposit1 = new Deposit();
			//deposit1.setDepositValue(20000L);
			//depositRepositry.saveAll(List.of(deposit1));
			
			
			//Deposit deposit1 = new Deposit();
			//Deposit depo1 = new Deposit(new Date(), -20L, true, user1);
			//deposit1.setUserAccount(user1);
			
			//depositRepositry.saveAll(List.of(deposit1));
		};
	}
}
