package com.ecom.deposit;

import java.time.LocalDate;
import java.util.Comparator;

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

/*
 * 	Tabelle Zahlungen
 */

@Entity
@Table(name = "deposit")
public class Deposit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	private Long depositValue;
	private String description;
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
		this.date = LocalDate.now();
		this.depositValue = 0L;
		this.description = "tmp Description";
		this.authorized = false;
	}
	
	public Deposit(LocalDate date, Long depositValue, String description, boolean authorized, UserAccount userAccount) {
		super();
		this.date = date;
		this.depositValue = depositValue;
		this.description = description;
		this.authorized = authorized;
		this.userAccount = userAccount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getDepositValue() {
		return depositValue;
	}

	public void setDepositValue(Long depositValue) {
		this.depositValue = depositValue;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	

	public static Comparator<Deposit> dateComparator = Comparator.comparing(Deposit::getDate);
	public static Comparator<Deposit> dateComparatorRev = Comparator.comparing(Deposit::getDate).reversed();
	
}
