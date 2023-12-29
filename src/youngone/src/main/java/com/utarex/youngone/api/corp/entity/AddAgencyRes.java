package com.utarex.youngone.api.corp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddAgencyRes {
	@JsonProperty private String issuedNo;
}
