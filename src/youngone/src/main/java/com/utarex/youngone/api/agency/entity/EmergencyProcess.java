package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmergencyProcess {
	@JsonProperty private String mcId;
	private String agncyId;
	@JsonProperty private int seq;
	@JsonProperty private String symptCntn;
	@JsonProperty private String causeDesc;
	@JsonProperty private String actnCntn;
}
