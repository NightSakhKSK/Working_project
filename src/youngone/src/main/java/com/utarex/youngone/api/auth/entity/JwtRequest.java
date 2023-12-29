package com.utarex.youngone.api.auth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class JwtRequest {
	@JsonProperty
	private String id;
	@JsonProperty
    private String pw;
}
