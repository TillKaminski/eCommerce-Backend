package com.ecom.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ecom.deposit.Deposit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/*
 * 
 * 	Tabelle Benutzer-Konten
 * 
 */


@Entity
@Table(name = "useraccount")
public class UserAccount implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private long id;
	// TODO DO Hash fuer UserId!!!
	private String firstName;
	private String lastName;
	private String email;
	private long balance;
	private long numberTransactions;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	private String password;
	@OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL) //, fetch = FetchType.LAZY)
	private List<Deposit> deposit = new ArrayList<>();

	
	public UserAccount() {
		super();
		this.id = -1L;
		this.firstName = "temp_firstName";
		this.lastName = "temp_lastName";
		this.email = "temp_eMail";
		this.balance = 1000L;
		this.numberTransactions = 0L;
		this.userRole = UserRole.USER;
	}
	
	public UserAccount(String firstName, String lastName, String eMail, long balance, UserRole userRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = eMail;
		this.balance = balance;
		this.numberTransactions = 0L;
		this.userRole = userRole;
	}
	
	public long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMail() {
		return email;
	}
	public void seteMail(String eMail) {
		this.email = eMail;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<Deposit> getDeposit() {
		return deposit;
	}
	public void setDeposit(List<Deposit> deposit) {
		this.deposit = deposit;
	}

	
	// TODO PW in Klasse sicher?
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", eMail=" + email
				+ ", balance=" + balance + ", userRole=" + userRole + ", deposit=" + deposit + "]"; // + ", deposit=" + deposit
	}


	public long getNumberTransactions() {
		return numberTransactions;
	}

	public void setNumberTransactions(long numberTransactions) {
		this.numberTransactions = numberTransactions;
	}


	public static Comparator<UserAccount> balanceComparator = Comparator.comparingLong(UserAccount::getBalance);
	public static Comparator<UserAccount> balanceComparatorRev = Comparator.comparingLong(UserAccount::getBalance).reversed();
	
	
}

