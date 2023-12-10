package com.ecom.deposit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.user.UserAccount;

@Service
public class DepositService {

	private final DepositRepository depositRepository;
	
	public DepositService(DepositRepository depositRepository) {
		this.depositRepository = depositRepository;
	}	
	
	// Zahlungen auf/absteigend nach Datum sortiert, Komparator in Klasse implementiert
	public List<Deposit> getDepositSorted(boolean reverse) {
		List<Deposit> depositSort = depositRepository.findAll();
		if (!reverse) {
			depositSort.sort(Deposit.dateComparator);
		} else {
			depositSort.sort(Deposit.dateComparatorRev);
		}		
		return depositSort;
	}
	
	// Zahlungen in Zeitraum
	public List<Deposit> getDepositPeriod(String strDateBegin, String strDateEnd) {
		List<Deposit> depositPeriod = depositRepository.findAll();
		depositPeriod.sort(Deposit.dateComparator); //Reihenfolge kann Filterung beschleunigen (Exit For)
		
		List<Deposit> resultDepositPeriod = new ArrayList<Deposit>();
		
		LocalDate dateBegin;
		LocalDate dateEnd;
		
		Boolean boolBegin = false;
		Boolean boolEnd = false;
		
		String dateFormat = "yyyy-MM-dd";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
		
		try {
			dateBegin = LocalDate.parse(strDateBegin, dateFormatter);
			dateEnd = LocalDate.parse(strDateEnd, dateFormatter);	
		} catch (RuntimeException e) {
			
			dateBegin = null;
			dateEnd = null;
			
			System.out.println("Format(Date) invalid!");
			System.out.println("Error: " + e.getMessage());
		}
		
		// TODO Filterung nach Datum im Service sinnvoll? Auslagerung in Repo möglich
		for (Deposit deposit : depositPeriod) {

			if (dateBegin != null) {
				boolBegin = (deposit.getDate().isAfter(dateBegin) || deposit.getDate().equals(dateBegin)) ? true : false;
			} else {
				boolBegin = true;
			}
			if (dateEnd != null) {
				boolEnd = (deposit.getDate().isBefore(dateEnd) || deposit.getDate().equals(dateEnd)) ? true : false;
			} else {
				boolEnd = true;
			}
			
			if (boolBegin && boolEnd) {
				resultDepositPeriod.add(deposit);
			}			
		}
		
		return resultDepositPeriod;
	}
	
	
	// TODO Testen!!!
	// Summe der Zahlungen in bestimmten Zeitraum, Zugriff auf "getDepositPeriod"?!
	public Long getDepositSumPeriod(String strDateBegin, String strDateEnd) {
		
		Long resultSumPeriod = 0L;
		List<Deposit> depositSumPeriod = this.getDepositPeriod(strDateBegin, strDateEnd);
		
		for (Deposit deposit : depositSumPeriod) {
			resultSumPeriod += deposit.getDepositValue();
		}
		
		return resultSumPeriod;
		
		/*
		List<Deposit> depositSumPeriod = depositRepository.findAll();
		depositSumPeriod.sort(Deposit.dateComparator); // Reihenfolge richtig?

		Long sumDeposit = 0L;
		LocalDate dateBegin;
		LocalDate dateEnd;

		Boolean boolBegin = false;
		Boolean boolEnd = false;

		String dateFormat = "yyyy-MM-dd";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

		try {
			dateBegin = LocalDate.parse(strDateBegin, dateFormatter);
			dateEnd = LocalDate.parse(strDateEnd, dateFormatter);
		} catch (RuntimeException e) {

			dateBegin = null;
			dateEnd = null;

			System.out.println("Format(Date) invalid!");
			System.out.println("Error: " + e.getMessage());
		}

		for (Deposit deposit : depositSumPeriod) {
			// TODO Format Datum konvertieren/prüfen
			// dateBegin oder dateEnd => kein Datum gesetzt => keine Grenze für Einträge
			if (dateBegin != null) {
				boolBegin = (deposit.getDate().isAfter(dateBegin) || deposit.getDate().equals(dateBegin)) ? true
						: false;
			} else {
				boolBegin = true;
			}
			if (dateEnd != null) {
				boolEnd = (deposit.getDate().isBefore(dateEnd) || deposit.getDate().equals(dateEnd)) ? true : false;
			} else {
				boolEnd = true;
			}

			sumDeposit = (boolBegin && boolEnd) ? sumDeposit + deposit.getDepositValue() : sumDeposit;

			// TODO Exit for möglich, da Liste sortiert

		}
		return sumDeposit;
		*/
	}

	public List<Deposit> getDepositByUserId(Long userId) {
		return depositRepository.findDepositByUserAccountId(userId).get();
	}
	
	public List<Deposit> getDepositByUserIdSorted(Long userId, boolean reverse) {
		List<Deposit> deposit = depositRepository.findDepositByUserAccountId(userId).get();
		
		if (!reverse) {
			deposit.sort(Deposit.dateComparator);
		} else {
			deposit.sort(Deposit.dateComparatorRev);
		}
		return deposit;
	}
	
	public Deposit addDeposit(UserAccount userAccount, Deposit deposit) {
		if (deposit.getDepositValue() > 0L) {deposit.setAuthorized(true);}; // TODO Zahlungen > 0 immer moeglich?! eventuell anpassen
		deposit.setUserAccount(userAccount);
		this.depositRepository.save(deposit);
		return deposit;
	}
	
	
}
