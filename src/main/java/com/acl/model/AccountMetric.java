package com.acl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACCOUNT_METRIC")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountMetric {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_KEY", insertable = false, updatable = false)
	private AccountMaster accountMaster;

	@Id
	@Column(name = "ACCOUNT_METRIC_KEY")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_metric_seq")
	@SequenceGenerator(
			name = "account_metric_seq",
			sequenceName = "account_metric_seq",
			allocationSize = 1,
			initialValue = 10000000
	)
	private BigInteger accountMetricKey;

	@Column(name = "ACCOUNT_KEY")
	private BigInteger accountKey;

	@Column(name = "MARKET_VAL")
	private BigDecimal marketVal;

	@Column(name = "PRINCIPAL_FEE_MTD")
	private BigDecimal principalFeeMtd;

	@Column(name = "PRINCIPAL_CASH")
	private BigDecimal principalCash;

	@Column(name = "LOAD_DATE")
	private Date loadDate;

	@Column(name = "SNAPSHOT_DATE")
	private Date snapShotDate;

}
