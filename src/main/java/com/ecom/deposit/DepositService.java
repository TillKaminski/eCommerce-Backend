package com.ecom.deposit;

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
	public List<Deposit> getDepositSorted(String sortField, boolean reverse) {
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
	
	public List<Deposit> getDepositSumPeriod(Date dateBegin, Date dateEnd) {
		List<Deposit> depositSumPeriod = depositRepository.findAll();
		return depositSumPeriod;
	}

	public List<Deposit> getDepositByUserId(Long userId) {
		// TODO Anpassen: zu userId Zahlungen finden
		return depositRepository.findDepositByUserAccountId(userId).get();
	}
	
	public List<Deposit> getDepositByUserIdSorted(Long userId) {
		// TODO Anpassen: zu userId Zahlungen finden
		List<Deposit> deposit = depositRepository.findDepositByUserAccountId(userId).get();
		deposit.sort(Deposit.dateComparator);
		return deposit;
	}
	
	public Deposit addDeposit(UserAccount userAccount, Deposit deposit) {
		if (deposit.getDepositValue() > 0L) {deposit.setAuthorized(true);}; // TODO Zahlungen > 0 immer moeglich?! eventuell anpassen
		deposit.setUserAccount(userAccount);
		this.depositRepository.save(deposit);
		return deposit;
	}
	
	
}
