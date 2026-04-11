package com.acl.controller;

import com.acl.datafilter.AccountMetricFilter;
import com.acl.response.AccountMetricResponse;
import com.acl.service.AccountMetricService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AccountMetricController {

	private final AccountMetricService accountMetricService;

	@QueryMapping
	public List<AccountMetricResponse> getAccountMetrics(@Argument AccountMetricFilter filter) {
		return accountMetricService.getAccountMetrics(filter);
	}
}
