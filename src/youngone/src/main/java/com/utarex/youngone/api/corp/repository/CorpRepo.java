package com.utarex.youngone.api.corp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.utarex.youngone.api.agency.entity.AgencyUserInfo;
import com.utarex.youngone.api.agency.entity.Emergency;
import com.utarex.youngone.api.agency.entity.Order;
import com.utarex.youngone.api.agency.entity.ProcessInfo;
import com.utarex.youngone.api.corp.entity.AddAgency;
import com.utarex.youngone.api.corp.entity.Corp;
import com.utarex.youngone.api.corp.entity.Distribute;
import com.utarex.youngone.api.user.entity.Chpw;

@Mapper
public interface CorpRepo {
	Corp getInfo();
	int addAgency(AddAgency agency);
	ProcessInfo getProcess();
	List<Distribute> getDistribute();
	List<Order> getOrderList(AgencyUserInfo aui);
	List<Emergency> getEmergency(AgencyUserInfo aui);
	int changePw(Chpw chpw);
}
