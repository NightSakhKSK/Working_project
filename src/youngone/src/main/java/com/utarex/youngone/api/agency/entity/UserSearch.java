package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserSearch {
	@JsonProperty private String text;
	private String agncyId;
}
