package com.acl.datafilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountMetricFilter {

	private String accountNumber;
	private String taxId;
	private String parentAccountNumber;
	private String startSnapshotDate;
	private String endSnapshotDate;
	int page;
	int size;
}
