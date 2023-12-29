package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Order {
    @JsonProperty private int orderSeq;
    @JsonProperty private int orderDtlSeq;
    @JsonProperty private String mcId;
    @JsonProperty private String orderDtti;
    @JsonProperty private String procYn;
    @JsonProperty private String procDtti;
    @JsonProperty private String mdCtgrCd;
    @JsonProperty private int orderQty;
    @JsonProperty private String tp;
}
