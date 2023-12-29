package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AddUser {
	@JsonProperty private String mcId;
	@JsonProperty private String agncyId;
    @JsonProperty private String buyerNm;
    @JsonProperty private String telNo;
    @JsonProperty private String instLocDesc;
    @JsonProperty private String awsId;
    @JsonProperty private String awsKey;
    @JsonProperty private String tempIssdNo;
}
