package com.acl.service;

import com.acl.datafilter.AccountFilter;
import com.acl.model.AccountMaster;
import com.acl.models.CreateAccountInput;
import com.acl.models.UpdateAccountInput;
import java.util.List;
import java.util.Optional;

public interface AccountMasterService {

	public List<AccountMaster> getAccounts(AccountFilter filter);

	Optional<AccountMaster> getAccountById(String id);

	AccountMaster createAccount(CreateAccountInput input);

	AccountMaster updateAccount(String id, UpdateAccountInput input);

	boolean deleteAccount(String id);
}
