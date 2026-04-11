package com.acl.controller;

import com.acl.datafilter.AccountFilter;
import com.acl.model.AccountMaster;
import com.acl.models.CreateAccountInput;
import com.acl.models.UpdateAccountInput;
import com.acl.service.AccountMasterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("acl/accounts")
public class AccountMasterController {

	private final AccountMasterService accountMasterService;

	// ── QUERIES ──────────────────────────────────────────────────────────────

	@QueryMapping
	public List<AccountMaster> getAccounts(@Argument("filter") AccountFilter filter) {
		return accountMasterService.getAccounts(filter);
	}

	@QueryMapping
	public AccountMaster getAccountById(@Argument("id") String id) {
		return accountMasterService.getAccountById(id)
				.orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
	}

	// ── MUTATIONS ─────────────────────────────────────────────────────────────

	@MutationMapping
	public AccountMaster createAccount(@Argument("input") CreateAccountInput input) {
		return accountMasterService.createAccount(input);
	}

	@MutationMapping
	public AccountMaster updateAccount(@Argument("id") String id,
			@Argument("input") UpdateAccountInput input) {
		return accountMasterService.updateAccount(id, input);
	}

	@MutationMapping
	public Boolean deleteAccount(@Argument("id") String id) {
		return accountMasterService.deleteAccount(id);
	}
}
