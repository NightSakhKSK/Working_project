package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Emergency {
	@JsonProperty private String mcId;
	@JsonProperty private int seq;
	@JsonProperty private String date;
    @JsonProperty private String emgcyTpCd;
	@JsonProperty private String visitCntn;
	@JsonProperty private String actnCntn;
    @JsonProperty private String statCd;
}
