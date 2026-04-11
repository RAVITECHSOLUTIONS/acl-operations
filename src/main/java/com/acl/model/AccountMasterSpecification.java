package com.acl.model;

import com.acl.datafilter.AccountFilter;
import com.acl.util.ACLConstants;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class AccountMasterSpecification {

	public static Specification<AccountMaster> filter(AccountFilter filter) {
		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();
			if (filter.getAccountNumber() != null && !filter.getAccountNumber().isEmpty()) {
				predicates.add(root.get(ACLConstants.ACCOUNT_NUMBER).in(filter.getAccountNumber()));
			}
			if (filter.getParentAccountNumber() != null && !filter.getParentAccountNumber().isEmpty()) {
				predicates.add(
						root.get(ACLConstants.PARENT_ACCOUNT_NUMBER).in(filter.getParentAccountNumber()));
			}
			if (filter.getAccountName() != null && !filter.getAccountName().isEmpty()) {
				predicates.add(root.get(ACLConstants.ACCOUNT_NAME).in(filter.getAccountName()));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
