package com.acl.service;

import com.acl.dao.entity.AccountMaster;
import com.acl.datafilter.AccountFilter;
import com.acl.models.CreateAccountInput;
import com.acl.models.UpdateAccountInput;
import java.util.List;
import java.util.Optional;

public interface AccountMasterService {

	public List<AccountMaster> getAccounts(AccountFilter filter);

	Optional<AccountMaster> getAccountById(Long id);

	AccountMaster createAccount(CreateAccountInput input);

	AccountMaster updateAccount(Long id, UpdateAccountInput input);

	boolean deleteAccount(Long id);
}
