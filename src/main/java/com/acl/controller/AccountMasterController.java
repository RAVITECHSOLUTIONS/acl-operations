package com.acl.controller;

import com.acl.dao.entity.AccountMaster;
import com.acl.datafilter.AccountFilter;
import com.acl.service.AccountMasterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("acl/accounts")
public class AccountMasterController {

	private final AccountMasterService accountMasterService;

	@PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AccountMaster>> getAccounts(@RequestBody AccountFilter accountFilter) {
		return ResponseEntity.ok(this.accountMasterService.getAccounts(accountFilter));
	}
}
