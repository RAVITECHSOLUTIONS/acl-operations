package com.acl.service;

import com.acl.datafilter.AccountMetricFilter;
import com.acl.response.AccountMetricResponse;
import java.util.List;

public interface AccountMetricService {

	List<AccountMetricResponse> getAccountMetrics(AccountMetricFilter filter);
}
