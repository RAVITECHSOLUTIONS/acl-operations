package com.acl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SNAPSHOT_CONTROL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnapShotControl {

	@Id
	@Column(name = "CURRENT_SNAPSHOT_DATE")
	private Date currentSnapShotDate;

	@Column(name = "SRC_MNEMONIC")
	private String srcMnemonic;

	@Column(name = "LOAD_DATE")
	private Date loadDate;

	@Column(name = "LOAD_BY")
	private String loadBy;

	@Column(name = "UPDATE_DATE")
	private String updateDate;

	@Column(name = "UPDATE_BY")
	private String updateBy;

	@Column(name = "PREVIOUS_SNAPSHOT_DATE")
	private Date previousSnapShotDate;
}
