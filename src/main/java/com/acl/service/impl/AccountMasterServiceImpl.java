package com.acl.service.impl;

import com.acl.dao.entity.AccountMaster;
import com.acl.dao.entity.AccountMasterSpecification;
import com.acl.dao.repository.AccountMasterRepository;
import com.acl.datafilter.AccountFilter;
import com.acl.service.AccountMasterService;
import com.acl.util.CommonUtil;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@AllArgsConstructor
public class AccountMasterServiceImpl implements AccountMasterService {

	private static final Logger logger = LoggerFactory.getLogger(AccountMasterServiceImpl.class);

	private final AccountMasterRepository accountMasterRepository;

	@Override
	public List<AccountMaster> getAccounts(AccountFilter filter) {
		logger.info("GQL call to get accounts data with new attributes for given filter: {}", filter);
		List<AccountMaster> accountData;
		validateGetAccountFilter(filter);
		return accountMasterRepository.findAll(AccountMasterSpecification.filter(filter));
	}

	private void validateGetAccountFilter(AccountFilter filter) {
		boolean hasSearchCriteria =
				filter != null && (!CollectionUtils.isEmpty(filter.getAccountNumber())
						|| !CollectionUtils.isEmpty(filter.getTaxId()) || !CollectionUtils.isEmpty(
						filter.getParentAccountNumber()) || !CommonUtil.isEmptyOrNull(filter.getAccountName()));
	}
}
