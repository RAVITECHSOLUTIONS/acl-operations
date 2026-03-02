package com.acl.dao.repository;

import com.acl.dao.entity.AccountMaster;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AccountMasterRepository extends JpaRepository<AccountMaster, BigInteger>,
		JpaSpecificationExecutor<AccountMaster> {

	public List<AccountMaster> findAllByAccountNumberIn(List<String> accountNumber);

	@Query("""
			SELECT c FROM AccountMaster c WHERE c.accountNumber IN ?1)
			""")
	public List<AccountMaster> getAccountMasterByAccountNumbers(List<String> accountNumbers);


	@Query("""
			SELECT c FROM AccountMaster c WHERE c.accountNumber =?1)
			""")
	public AccountMaster getAccountMasterByAccountNumber(String accountNumber);
}
