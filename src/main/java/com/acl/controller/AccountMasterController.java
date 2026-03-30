package com.acl.controller;

import com.acl.dao.entity.AccountMaster;
import com.acl.datafilter.AccountFilter;
import com.acl.models.CreateAccountInput;
import com.acl.models.UpdateAccountInput;
import com.acl.service.AccountMasterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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

//	@PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<AccountMaster>> getAccounts(@RequestBody AccountFilter accountFilter) {
//		return ResponseEntity.ok(this.accountMasterService.getAccounts(accountFilter));
//	}

	// ── QUERIES ──────────────────────────────────────────────────────────────

	@QueryMapping
	public List<AccountMaster> getAccounts(@Argument("filter") AccountFilter filter) {
		return accountMasterService.getAccounts(filter);
	}

	@QueryMapping
	public AccountMaster getAccountById(@Argument("id") Long id) {
		return accountMasterService.getAccountById(id)
				.orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
	}

	// ── MUTATIONS ─────────────────────────────────────────────────────────────

	@MutationMapping
	public AccountMaster createAccount(@Argument("input") CreateAccountInput input) {
		return accountMasterService.createAccount(input);
	}

	@MutationMapping
	public AccountMaster updateAccount(@Argument("id") Long id,
			@Argument("input") UpdateAccountInput input) {
		return accountMasterService.updateAccount(id, input);
	}

	@MutationMapping
	public Boolean deleteAccount(@Argument("id") Long id) {
		return accountMasterService.deleteAccount(id);
	}
}
