package com.ecom.deposit;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long>{
	
	Optional<List<Deposit>> findDepositByUserAccountId (Long userAccountId);

}
