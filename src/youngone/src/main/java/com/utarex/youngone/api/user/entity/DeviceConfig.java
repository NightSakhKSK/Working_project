package com.utarex.youngone.api.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeviceConfig {
	@JsonProperty private String mcId;
    @JsonProperty private String runTime;
    @JsonProperty private String iceOutTime;
    @JsonProperty private String coldOutTime;
    @JsonProperty private String carbonOutTime;
    @JsonProperty private String hotOutTime;
    @JsonProperty private String adeIceSmallOutTime;
    @JsonProperty private String adeIceBigOutTime;
    @JsonProperty private String adeCarbonLowOutTime;
    @JsonProperty private String adeCarbonBasicOutTime;
    @JsonProperty private String adeCarbonHighOutTime;
    @JsonProperty private String adeSyrupSmallOutTime;
    @JsonProperty private String adeSyrupBasicOutTime;
    @JsonProperty private String adeSyrupBigOutTime;
}
