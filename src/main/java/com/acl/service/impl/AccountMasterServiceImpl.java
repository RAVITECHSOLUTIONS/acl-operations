package com.acl.service.impl;

import com.acl.dao.entity.AccountMaster;
import com.acl.dao.entity.AccountMasterSpecification;
import com.acl.dao.repository.AccountMasterRepository;
import com.acl.datafilter.AccountFilter;
import com.acl.models.CreateAccountInput;
import com.acl.models.UpdateAccountInput;
import com.acl.service.AccountMasterService;
import com.acl.util.CommonUtil;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

	@Override
	public AccountMaster createAccount(CreateAccountInput input) {
		logger.info("GQL call to create account: {}", input.getAccountNumber());

		if (accountMasterRepository.existsByAccountNumber(input.getAccountNumber())) {
			throw new RuntimeException("Account number already exists: " + input.getAccountNumber());
		}

		AccountMaster account = AccountMaster.builder()
				.accountNumber(input.getAccountNumber())
				.parentAccountNumber(input.getParentAccountNumber())
				.accountName(input.getAccountName())
				.accountTypeCd(input.getAccountType())
				.build();

		return accountMasterRepository.save(account);
	}

	@Override
	public AccountMaster updateAccount(Long id, UpdateAccountInput input) {
		logger.info("GQL call to update account id: {}", id);

		AccountMaster account = accountMasterRepository.findById(BigInteger.valueOf(id))
				.orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

		if (input.getParentAccountNumber() != null) {
			account.setParentAccountNumber(input.getParentAccountNumber());
		}
		if (input.getAccountName() != null) {
			account.setAccountName(input.getAccountName());
		}
		if (input.getAccountType() != null) {
			account.setAccountTypeCd(input.getAccountType());
		}
		return accountMasterRepository.save(account);
	}

	@Override
	public boolean deleteAccount(Long id) {
		logger.info("GQL call to delete account id: {}", id);

		if (!accountMasterRepository.existsById(BigInteger.valueOf(id))) {
			throw new RuntimeException("Account not found with id: " + id);
		}
		accountMasterRepository.deleteById(BigInteger.valueOf(id));
		return true;
	}

	@Override
	public Optional<AccountMaster> getAccountById(Long id) {
		logger.info("GQL call to get account by id: {}", id);
		return accountMasterRepository.findById(BigInteger.valueOf(id));
	}

	private void validateGetAccountFilter(AccountFilter filter) {
		boolean hasSearchCriteria =
				filter != null && (!CollectionUtils.isEmpty(filter.getAccountNumber())
						|| !CollectionUtils.isEmpty(filter.getParentAccountNumber())
						|| !CommonUtil.isEmptyOrNull(filter.getAccountName()));
		logger.debug("Filter has search criteria: {}", hasSearchCriteria);
	}
}
