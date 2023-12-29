package com.utarex.youngone.api.device.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceStatusIoT {
	@JsonProperty("id")                         private String mcId;
	@JsonProperty("runtime")                    private String runtime;
	@JsonProperty("type")                       private String messageType;
	@JsonProperty("action")                     private String action;
    @JsonProperty("ice_s")                      private String iceStat;
    @JsonProperty("cold_s")                     private String cwStat;
    @JsonProperty("hot_s")                      private String hwStat;
    @JsonProperty("carbon_s")                   private String spklStat;
    @JsonProperty("leak_s")                     private String wlStat;
    @JsonProperty("drain_s")                    private String woStat;
    @JsonProperty("hit_s")                      private String hitStat;
    @JsonProperty("comp_s")                     private String compStat; //new
    @JsonProperty("boost_s")                    private String boostStat; //new
    @JsonProperty("tur1_v")                     private String tbdt1Sensr;
    @JsonProperty("tur2_v")                     private String tbdt2Sensr;
    @JsonProperty("ph_v")                       private String phSensr;
    @JsonProperty("tds1_v")                     private String wq1Sensr;
    @JsonProperty("tds2_v")                     private String wq2Sensr;
    @JsonProperty("s1_r")                       private String syrup1Remn;
    @JsonProperty("s2_r")                       private String syrup2Remn;
    @JsonProperty("s3_r")                       private String syrup3Remn;
    @JsonProperty("s4_r")                       private String syrup4Remn;
    @JsonProperty("gas_r")                      private String gasRchrgRemn;
    @JsonProperty("u_cold_r")                   private String cwTankUvcRemn;
    @JsonProperty("u_carbon_r")                 private String spklTankUvcRemn;
    @JsonProperty("u_ice_r")                    private String iceTankUvcRemn;
    @JsonProperty("u_drink_r")                  private String drkEoUvcRemn;
    @JsonProperty("u_ice_o_r")                  private String iceEoUvcRemn;
    @JsonProperty("sw_ver")                     private String swVer;
    @JsonProperty("fw_ver")                     private String fwVer;
    @JsonProperty("alarm_e")                    private String alarm_e;
    @JsonProperty("alarm_c")                    private String alarm_c;
}
