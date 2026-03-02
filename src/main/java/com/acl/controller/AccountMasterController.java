package com.acl.controller;

import com.acl.dao.entity.AccountMaster;
import com.acl.datafilter.AccountFilter;
import com.acl.service.AccountMasterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AccountMasterController {

	private final AccountMasterService accountMasterService;

	public ResponseEntity<List<AccountMaster>> getAccounts(AccountFilter accountFilter) {
		return ResponseEntity.ok(this.accountMasterService.getAccounts(accountFilter));
	}

}
