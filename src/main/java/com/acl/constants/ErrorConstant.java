package com.acl.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstant {

	public static final String ACCOUNT_NOT_FOUNT_FOR_INPUT = "Missing required input (accountNumber) for Account filter: ";

	public static final String ACCOUNT_METRIC_INVALID_INPUT = "Missing required input (accountNumber) for Account metric filter: ";

	public static final String INVALID_DATE_FORMAT = "Invalid date format(Please enter date in yyyy-MM-dd format)";

}
