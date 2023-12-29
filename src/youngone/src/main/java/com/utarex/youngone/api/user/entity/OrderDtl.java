package com.utarex.youngone.api.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderDtl {
	@JsonProperty private int orderSeq;
	@JsonProperty private int orderDtlSeq;
	@JsonProperty private String usrId;
	@JsonProperty private String category;
	@JsonProperty private String id;
	@JsonProperty private String name;
    @JsonProperty private int value;
    @JsonProperty private String orderDt;
}
