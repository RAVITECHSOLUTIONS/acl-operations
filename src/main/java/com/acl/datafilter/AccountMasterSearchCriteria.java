package com.acl.datafilter;

import java.util.List;
import lombok.Data;

@Data
public class AccountMasterSearchCriteria {

	private List<String> accountNumbers;
	private List<String> taxIds;
	private List<String> parentAccountNUmber;
	private String accountName;
	private List<String> feeConsId;
}
