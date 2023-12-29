package com.utarex.youngone.api.auth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
		
	@JsonProperty
	private String token;
}
