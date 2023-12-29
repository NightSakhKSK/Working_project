package com.utarex.youngone.api.corp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Corp {
	@JsonProperty private int devices;
    @JsonProperty private int filters;
    @JsonProperty private int nonfilters;
    @JsonProperty private int emergency;
}
