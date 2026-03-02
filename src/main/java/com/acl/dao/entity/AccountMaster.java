package com.acl.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigInteger;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name = "ACCOUNT_MASTER")
@Data
public class AccountMaster {

	@Id
	@Column(name = "ACCOUNT_KEY")
	private BigInteger accountKey;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name = "PARENT_ACCOUNT_NUMBER")
	private String parentAccountNumber;

	@Column(name = "ACCOUNT_NAME")
	private String accountName;

	@Column(name = "ACCOUNT_TYPE_CD")
	private String accountTypeCd;

	@Column(name = "ACCOUNT_TYPE_DESC")
	private String accountTypeDesc;

	@Column(name = "INCEPTION_TAX_DATE")
	private Date inceptionTaxDate;

	@Column(name = "INV_OFFICER_NAME")
	private String invOfficerName;
}
