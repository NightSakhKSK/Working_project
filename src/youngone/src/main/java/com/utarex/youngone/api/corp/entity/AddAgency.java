package com.utarex.youngone.api.corp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AddAgency {
	@JsonProperty private String addr;
    @JsonProperty private String bsnLcnNo;
    @JsonProperty private String mngrNm;
    @JsonProperty private String telNo;
    private String admrId;
    private String tempIssdNo;
}
