package com.ecom.payment;

import java.util.Date;

import com.ecom.user.UserAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Deposit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date date;
	private Long depositValue;
	private boolean authorized;
	@ManyToOne
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;
	
	public Deposit() {
		super();
		this.id = -1L;
		this.date = new Date();
		this.depositValue = 0L;
		this.authorized = false;
	}
	
	public Deposit(Date date, Long depositValue, boolean authorized) {
		super();
		this.date = date;
		this.depositValue = depositValue;
		this.authorized = authorized;
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
	
	
	
	
	
	

}
