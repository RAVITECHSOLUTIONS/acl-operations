package com.acl.models;

import java.util.Date;
import lombok.Data;

@Data
public class UpdateAccountInput {

	private String accountName;

	private String accountNumber;

	private String parentAccountNumber;

	private String accountTypeCd;

	private String accountTypeDesc;

	private String accountCtgryDesc;

	private Date inceptionTaxDate;

	private String invOfficerName;
}
