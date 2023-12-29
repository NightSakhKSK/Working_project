package com.utarex.youngone.api.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Install {
	@JsonIgnore private String usrId;
    @JsonProperty private String mcId;
    @JsonProperty private String installDt;
    @JsonProperty private String mngAgncyNm;
    @JsonProperty private String mngAgncyTelNo;
}
