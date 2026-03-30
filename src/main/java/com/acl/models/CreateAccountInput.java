package com.acl.models;

import lombok.Data;

@Data
public class CreateAccountInput {

	private String accountNumber;
	private String parentAccountNumber;
	private String accountName;
	private String accountType;
	private String status;
}
