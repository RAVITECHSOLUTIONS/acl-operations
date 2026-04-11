package com.acl.models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import lombok.Data;

@Data
public class CreateAccountMetricInput {

	private BigInteger accountKey;

	private BigDecimal marketVal;

	private BigDecimal principalFeeMtd;

	private BigDecimal principalCash;

	private Date loadDate;

	private Date snapShotDate;
}
