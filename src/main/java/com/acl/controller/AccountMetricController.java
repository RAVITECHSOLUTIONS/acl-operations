package com.acl.controller;

import com.acl.datafilter.AccountMetricFilter;
import com.acl.model.AccountMetric;
import com.acl.models.CreateAccountMetricInput;
import com.acl.response.AccountMetricResponse;
import com.acl.service.AccountMetricService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AccountMetricController {

	private final AccountMetricService accountMetricService;

	// ── QUERIES ──────────────────────────────────────────────────────────────

//	@QueryMapping
//	public List<AccountMetricResponse> getAccountMetrics(@Argument AccountMetricFilter filter) {
//		return accountMetricService.getAccountMetrics(filter);
//	}

	@QueryMapping
	public List<AccountMetric> getAccountMetricsByAccountNumber(
			@Argument("filter") AccountMetricFilter filter) {
		return accountMetricService.getAccountMetricsByAccountNumber(filter);
	}

	// ── MUTATIONS ─────────────────────────────────────────────────────────────

	@MutationMapping
	public AccountMetric createAccountMetric(@Argument("input") CreateAccountMetricInput input) {
		return accountMetricService.createAccountMetric(input);
	}
}
