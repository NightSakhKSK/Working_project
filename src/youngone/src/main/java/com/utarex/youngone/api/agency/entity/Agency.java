package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Agency {
	@JsonProperty private String id;
    @JsonProperty private String nm;
    @JsonProperty private int devices;
    @JsonProperty private int filters;
    @JsonProperty private int nonfilters;
    @JsonProperty private int emergency;
}
