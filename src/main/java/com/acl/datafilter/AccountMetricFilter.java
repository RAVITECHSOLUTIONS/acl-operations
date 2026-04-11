package com.acl.datafilter;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountMetricFilter {

	private List<String> accountNumber;
	private List<String> taxId;
	private List<String> parentAccountNumber;
	private List<String> startSnapshotDate;
	private List<String> endSnapshotDate;
	int page;
	int size;
}
