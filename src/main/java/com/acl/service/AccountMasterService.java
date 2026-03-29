package com.acl.service;

import com.acl.dao.entity.AccountMaster;
import com.acl.datafilter.AccountFilter;
import java.util.List;

public interface AccountMasterService {

	public List<AccountMaster> getAccounts(AccountFilter filter);
}
