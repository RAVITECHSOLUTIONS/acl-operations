package com.acl.dao.repository;

import com.acl.dao.entity.AccountMaster;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountMasterRepository extends JpaRepository<AccountMaster, BigInteger>,
		JpaSpecificationExecutor<AccountMaster> {

	public List<AccountMaster> findAllByAccountNumberIn(List<String> accountNumber);

	@Query("""
			SELECT c FROM AccountMaster c WHERE c.accountNumber IN ?1
			""")
	public List<AccountMaster> getAccountMasterByAccountNumbers(
			@Param("accountNumbers") List<String> accountNumbers);


	@Query("""
			SELECT c FROM AccountMaster c WHERE c.accountNumber =?1
			""")
	public AccountMaster getAccountMasterByAccountNumber(
			@Param("accountNumber") String accountNumber);

	Optional<AccountMaster> findByAccountNumber(String accountNumber);

	boolean existsByAccountNumber(String accountNumber);
}
