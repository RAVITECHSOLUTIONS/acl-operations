package com.acl.repository;

import com.acl.constants.GraphQlConstants;
import com.acl.datafilter.AccountMasterSearchCriteria;
import com.acl.model.AccountMaster;
import com.acl.util.CommonUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
@Configuration
public class CustomQueryRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private static final String CLASS_NAME = CustomQueryRepository.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(CLASS_NAME);

	public List<AccountMaster> findAccountWithoutFees(AccountMasterSearchCriteria input) {
		final String METHOD_NAME = "findAccountWithoutFees";
		logger.debug(GraphQlConstants.LOG_ENTER_METHOD, METHOD_NAME);
		logger.info("method: {}, Input for account query => {}", METHOD_NAME, input);

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AccountMaster> cq = cb.createQuery(AccountMaster.class);
		Root<AccountMaster> accountMasterRoot = cq.from(AccountMaster.class);

		List<Predicate> predicates = new ArrayList<>();
		if (!CollectionUtils.isEmpty(input.getAccountNumbers())) {
			predicates.add(
					accountMasterRoot.get(GraphQlConstants.ACCOUNT_NUMBER).in(input.getAccountNumbers()));
		}
		if (!CollectionUtils.isEmpty(input.getTaxIds())) {
			predicates.add(accountMasterRoot.get(GraphQlConstants.TAX_ID).in(input.getTaxIds()));
		}
		if (!CollectionUtils.isEmpty(input.getParentAccountNUmber())) {
			predicates.add(accountMasterRoot.get(GraphQlConstants.PARENT_ACCOUNT_NUMBER)
					.in(input.getParentAccountNUmber()));
		}
		if (!CommonUtil.isEmptyOrNull(input.getAccountName())) {
			predicates.add(accountMasterRoot.get("accountName").in(input.getAccountName()));
		}

		cq.select(accountMasterRoot).where(predicates.toArray(new Predicate[0]));
		logger.debug(GraphQlConstants.LOG_EXIT_METHOD, METHOD_NAME);
		return entityManager.createQuery(cq).getResultList();
	}

}
