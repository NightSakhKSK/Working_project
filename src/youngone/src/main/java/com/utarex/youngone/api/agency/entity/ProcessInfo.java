package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProcessInfo {
    @JsonProperty private int filters;
    @JsonProperty private int nonfilters;
    @JsonProperty private int emergency;
}
