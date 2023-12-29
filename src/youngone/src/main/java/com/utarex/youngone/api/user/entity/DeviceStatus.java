package com.utarex.youngone.api.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeviceStatus {
	@JsonIgnore private String mcId;
    @JsonProperty private String iceStat;
    @JsonProperty private String cwStat;
    @JsonProperty private String hwStat;
    @JsonProperty private String spklStat;
    @JsonProperty private String wlStat;
    @JsonProperty private String woStat;
    @JsonProperty private String hitStat;
    @JsonProperty private String compStat;
    @JsonProperty private String boostStat;
    @JsonProperty private String tbdt1Sensr;
    @JsonProperty private String tbdt2Sensr;
    @JsonProperty private String phSensr;
    @JsonProperty private String wq1Sensr;
    @JsonProperty private String wq2Sensr;
    @JsonProperty private String syrup1Remn;
    @JsonProperty private String syrup2Remn;
    @JsonProperty private String syrup3Remn;
    @JsonProperty private String syrup4Remn;
    @JsonProperty private String gasRchrgStat;
    @JsonProperty private String cwTankUvcRemn;
    @JsonProperty private String spklTankUvcRemn;
    @JsonProperty private String iceTankUvcRemn;
    @JsonProperty private String drkEoUvcRemn;
    @JsonProperty private String iceEoUvcRemn;
    @JsonProperty private String swVer;
    @JsonProperty private String fwVer;
}
