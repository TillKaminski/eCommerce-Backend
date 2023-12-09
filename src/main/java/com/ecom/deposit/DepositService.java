package com.ecom.deposit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.user.UserAccount;

@Service
public class DepositService {

	private final DepositRepository depositRepository;
	
	public DepositService(DepositRepository depositRepository) {
		this.depositRepository = depositRepository;
	}	
	
	// TODO Zahlunge sortieren
	public List<Deposit> getDepositSorted(boolean reverse) {
		List<Deposit> depositSort = depositRepository.findAll();
		if (!reverse) {
			depositSort.sort(Deposit.dateComparator);
		} else {
			depositSort.sort(Deposit.dateComparatorRev);
		}		
		return depositSort;
	}
	
	// TODO Zahlungen Zeitraum, vorher sortieren?
	public List<Deposit> getDepositPeriod(Date dateBegin, Date dateEnd) {
		List<Deposit> depositPeriod = depositRepository.findAll();
		return depositPeriod;
	}
	
	
	//TODO Testen!!!
	public Long getDepositSumPeriod(String strDateBegin, String strDateEnd) {
		
		List<Deposit> depositSumPeriod = depositRepository.findAll();
		depositSumPeriod.sort(Deposit.dateComparator); //Reihenfolge richtig?
		
		
		System.out.println("_StringService " + strDateBegin);
		System.out.println("_StringService " + strDateEnd);
		
		Long sumDeposit = 0L;
		LocalDate dateBegin;
		LocalDate dateEnd;
		
		//LocalDate dateFallback = LocalDate.now();
		
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
			
			//dateBegin = dateFallback;
			//dateEnd = dateFallback;
			
			System.out.println("Format(Date) invalid!");
			System.out.println("Error: " + e.getMessage());
		}
		
		/*
		System.out.println("€€€1 Dateformat: " + dateBegin);
		System.out.println("€€€2 Dateformat: " + dateEnd);
		 */
		// TODO Datum vorher auswerten, vorher nacher
		
		
		
		
			for (Deposit deposit : depositSumPeriod) {
				// TODO Format Datum konvertieren/prüfen
				
				/*
				System.out.println("€€€B Dateformat: " + deposit.getDate().isAfter(dateBegin));
				System.out.println("€€€B Dateformat: " + deposit.getDate().isBefore(dateEnd));
				System.out.println("€€€B Dateformat: ___________________");
				*/
				
				// TODO dateBegin oder dateEnd => kein Datum gesetzt => keine Grenze für Einträge
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
				
				sumDeposit = (boolBegin && boolEnd) ? sumDeposit + deposit.getDepositValue() : sumDeposit;
				
				// TODO Exit for möglich, da Liste sortiert
				
				/*
				if (dateBegin != null && dateEnd != null) {
					sumDeposit = (deposit.getDate().isAfter(dateBegin) && deposit.getDate().isBefore(dateEnd)) ? sumDeposit + deposit.getDepositValue() : sumDeposit;
				}
				if (dateBegin != null && dateEnd == null) {
					sumDeposit = (deposit.getDate().isAfter(dateEnd)) ? sumDeposit + deposit.getDepositValue() : sumDeposit;
				}
				if (dateBegin == null && dateEnd != null) {
					sumDeposit = (deposit.getDate().isBefore(dateEnd)) ? sumDeposit + deposit.getDepositValue() : sumDeposit;
				}
				if (dateBegin == null && dateEnd == null) {
					sumDeposit =  sumDeposit + deposit.getDepositValue();
				}
				*/
			}
		return sumDeposit;
	}

	public List<Deposit> getDepositByUserId(Long userId) {
		// TODO Anpassen: zu userId Zahlungen finden
		return depositRepository.findDepositByUserAccountId(userId).get();
	}
	
	public List<Deposit> getDepositByUserIdSorted(Long userId, boolean reverse) {
		// TODO Anpassen: zu userId Zahlungen finden
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
