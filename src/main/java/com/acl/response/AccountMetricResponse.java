package com.acl.response;

import com.acl.model.AccountMetric;
import java.util.List;
import lombok.Data;

@Data
public class AccountMetricResponse {

	private String accountNumber;
	private String taxId;
	private String parentAccountNumber;
	private String consAccountNumber;
	private List<AccountMetric> accountMetrics;
}
