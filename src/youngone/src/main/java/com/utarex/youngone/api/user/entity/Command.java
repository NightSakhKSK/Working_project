package com.utarex.youngone.api.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Command {
	private String usrId;
	@JsonProperty private String action;
    @JsonProperty private String value;
	@JsonProperty private String mcId;
}
