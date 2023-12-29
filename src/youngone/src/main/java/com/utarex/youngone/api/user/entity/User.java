package com.utarex.youngone.api.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {
	@JsonIgnore private String usrId;
    @JsonProperty private String mcId;
    @JsonProperty private String buyerNm;
    @JsonProperty private int filters;
    @JsonProperty private int nonfilters;
    @JsonProperty private int emergency;
}
