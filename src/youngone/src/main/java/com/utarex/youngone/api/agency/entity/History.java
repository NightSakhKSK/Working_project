package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class History {
	@JsonProperty private int seq;
    @JsonProperty private String date;
    @JsonProperty private String emgcyTpCd;
    @JsonProperty private String statCd;
    @JsonProperty private String symptCntn;
	@JsonProperty private String causeDesc;
	@JsonProperty private String actnCntn;
}
