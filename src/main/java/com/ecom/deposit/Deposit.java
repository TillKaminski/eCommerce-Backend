package com.ecom.deposit;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ecom.user.UserAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deposit")
public class Deposit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date date;
	private Long depositValue;
	private boolean authorized;
	@ManyToOne
	@JoinColumn(name = "user_account_id") //, updatable = false, nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE) // nicht nutzen, da Zahlungen relevant
	@JsonIgnore	// wichtig fuer Antwort JSON -> sonst: Endlosschleife
	//@Column(updatable = false, nullable = false)
	private UserAccount userAccount;
	
	public Deposit() {
		super();
		this.id = -1L;
		this.date = new Date();
		this.depositValue = 0L;
		this.authorized = false;
		//this.userAccount = new UserAccount("Deposit Name", "Deposit lastName", "Deposit eMail", 1000L, UserRole.EMPLOYEE);
	}
	
	public Deposit(Date date, Long depositValue, boolean authorized, UserAccount userAccount) {
		super();
		this.date = date;
		this.depositValue = depositValue;
		this.authorized = authorized;
		this.userAccount = userAccount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getDepositValue() {
		return depositValue;
	}

	public void setDepositValue(Long depositValue) {
		this.depositValue = depositValue;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public Long getId() {
		return id;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	
	
	
	
	
	

}
