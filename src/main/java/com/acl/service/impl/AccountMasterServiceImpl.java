package com.acl.service.impl;


import com.acl.datafilter.AccountFilter;
import com.acl.model.AccountMaster;
import com.acl.model.AccountMasterSpecification;
import com.acl.models.CreateAccountInput;
import com.acl.models.UpdateAccountInput;
import com.acl.repository.AccountMasterRepository;
import com.acl.service.AccountMasterService;
import com.acl.util.CommonUtil;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

	@Override
	public AccountMaster createAccount(CreateAccountInput input) {
		logger.info("GQL call to create account: {}", input.getAccountNumber());

		if (accountMasterRepository.existsByAccountNumber(input.getAccountNumber())) {
			throw new RuntimeException("Account number already exists: " + input.getAccountNumber());
		}

		String accountNumber = input.getAccountNumber()
				.substring(input.getAccountNumber().length() - 8);
		BigInteger last8DigitsOfAccountNumber = new BigInteger(accountNumber);
		AccountMaster account = AccountMaster.builder().accountKey(last8DigitsOfAccountNumber)
				.accountName(input.getAccountName()).accountNumber(input.getAccountNumber())
				.parentAccountNumber(input.getParentAccountNumber()).accountTypeCd(input.getAccountTypeCd())
				.accountTypeDesc(input.getAccountTypeDesc()).accountCtgryDesc(input.getAccountCtgryDesc())
				.inceptionTaxDate(new Date()).invOfficerName(input.getInvOfficerName()).build();
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
		if (input.getAccountTypeCd() != null) {
			account.setAccountTypeCd(input.getAccountTypeCd());
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
	public Optional<AccountMaster> getAccountById(String id) {
		logger.info("GQL call to get account by id: {}", id);
		return accountMasterRepository.findById(new BigInteger(id));
	}

	private void validateGetAccountFilter(AccountFilter filter) {
		boolean hasSearchCriteria =
				filter != null && (!CollectionUtils.isEmpty(filter.getAccountNumber())
						|| !CollectionUtils.isEmpty(filter.getParentAccountNumber())
						|| !CommonUtil.isEmptyOrNull(filter.getAccountName()));
		logger.debug("Filter has search criteria: {}", hasSearchCriteria);
	}
}
