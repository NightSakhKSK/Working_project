package com.utarex.youngone.api.agency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utarex.youngone.api.user.entity.Command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AgencyUserInfo extends Command {
	@JsonProperty private String mcId;
	@JsonProperty private Integer seq;
	@JsonProperty private String agncyId;
	private boolean today;
}
