package com.acl.repository;

import com.acl.model.AccountMetric;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountMetricRepository extends JpaRepository<AccountMetric, Integer> {

	@Query("""
			SELECT am from AccountMetric am
			WHERE am.accountKey IN ?1 and (am.snapShotDate >= ?2) and (am.snapShotDate <= ?3)
			""")
	public List<AccountMetric> getAccountMetricsByAccountKeys(List<BigInteger> accountKey,
			Date startDate, Date endDate, Pageable pageable);

	@Query("""
			SELECT am from AccountMetric am
			WHERE am.accountKey IN ?1 and am.snapShotDate =?2
			""")
	public List<AccountMetric> getAccountMetricsBySnapShotDate(List<BigInteger> accountKey,
			Date snapShotDate);
}
