package com.utarex.youngone.api.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderMst {
	@JsonProperty private int orderSeq;
	@JsonProperty private String usrId;
}
