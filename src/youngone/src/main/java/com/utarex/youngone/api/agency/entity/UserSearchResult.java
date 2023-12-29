package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserSearchResult {
	@JsonProperty private String mcId;
	@JsonProperty private String buyerNm;
	@JsonProperty private String telNo;
	@JsonProperty private String instlDtti;
	@JsonProperty private String instlLocDesc;
}
