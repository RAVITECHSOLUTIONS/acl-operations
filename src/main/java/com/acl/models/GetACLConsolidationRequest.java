package com.acl.models;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class GetACLConsolidationRequest {

	@NotBlank(message = "opportunitySalesforceId may not be empty")
	private String opportunitySalesforceId;

	@NotBlank(message = "sourceSystem may not be empty")
	private String sourceSystem;

}
