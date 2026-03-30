package com.acl.controller;

import com.acl.dao.entity.AccountMaster;
import com.acl.datafilter.AccountFilter;
import com.acl.service.AccountMasterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AccountMasterGraphQLController {

	private final AccountMasterService accountMasterService;

//	@QueryMapping
//	public List<AccountMaster> getAccounts(@Argument("filter") AccountFilter filter) {
//		return accountMasterService.getAccounts(filter);
//	}
}
