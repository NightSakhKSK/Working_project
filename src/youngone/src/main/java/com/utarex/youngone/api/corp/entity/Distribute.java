package com.utarex.youngone.api.corp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Distribute {
	@JsonProperty private String agncyId;
	@JsonProperty private String agncyNm;
	@JsonProperty private int devices;
    @JsonProperty private int filters;
    @JsonProperty private int nonfilters;
    @JsonProperty private int emergency;
}
