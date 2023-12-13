package com.ecom.security.config;

import org.springframework.context.annotation.Configuration;


/*
 *  TODO DO	Spring Security implementieren
 */

@Configuration
public class SecurityConfig {
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
	//Veraltet
	    http
	        .authorizeRequests()
	            .antMatchers("/register", "/css/**", "/js/**").permitAll() // Zugriff auf Registrierung und statische Ressourcen
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/login")
	            .permitAll()
	            .and()
	        .logout()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login?logout")
	            .permitAll();
	}
	*/
}
