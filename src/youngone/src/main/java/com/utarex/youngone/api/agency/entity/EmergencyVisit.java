package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmergencyVisit {
	@JsonProperty private String mcId;
	private String agncyId;
	@JsonProperty private int seq;
	@JsonProperty private String plan;
}
