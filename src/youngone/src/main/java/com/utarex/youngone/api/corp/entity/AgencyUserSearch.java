package com.utarex.youngone.api.corp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AgencyUserSearch {
	@JsonProperty private String text;
	@JsonProperty private String agncyId;
}
