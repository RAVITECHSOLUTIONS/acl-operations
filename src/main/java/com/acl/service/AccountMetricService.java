package com.acl.service;

import com.acl.datafilter.AccountMetricFilter;
import com.acl.model.AccountMetric;
import com.acl.models.CreateAccountMetricInput;
import com.acl.response.AccountMetricResponse;
import java.util.List;

public interface AccountMetricService {

//	List<AccountMetricResponse> getAccountMetrics(AccountMetricFilter filter);

	AccountMetric createAccountMetric(CreateAccountMetricInput input);

	List<AccountMetric> getAccountMetricsByAccountNumber(AccountMetricFilter filter);
}
