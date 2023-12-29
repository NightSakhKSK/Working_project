package com.utarex.youngone.api.join.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserJoin {
	@JsonProperty private String id;
    @JsonProperty private String pw;
    @JsonProperty private String nm;
    @JsonProperty private String issuedNo;
}
