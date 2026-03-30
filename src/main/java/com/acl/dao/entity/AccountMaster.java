package com.acl.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountMaster implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ACCOUNT_KEY")
	private BigInteger accountKey;

	@Column(name = "ACCOUNT_NAME")
	private String accountName;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name = "PARENT_ACCOUNT_NUMBER")
	private String parentAccountNumber;

	@Column(name = "ACCOUNT_TYPE_CD")
	private String accountTypeCd;

	@Column(name = "ACCOUNT_TYPE_DESC")
	private String accountTypeDesc;

	@Column(name = "ACCOUNT_CTGRY_DESC")
	private String accountCtgryDesc;

	@Column(name = "INCEPTION_TAX_DATE")
	private Date inceptionTaxDate;

	@Column(name = "INV_OFFICER_NAME")
	private String invOfficerName;
}
