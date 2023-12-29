package com.utarex.youngone.api.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Chpw {
	@JsonProperty private String id;
	@JsonProperty private String oldPw;
	@JsonProperty private String newPw;
	@JsonProperty private String confirmPw;
}
