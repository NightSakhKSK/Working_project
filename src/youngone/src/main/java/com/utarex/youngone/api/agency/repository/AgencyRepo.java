package com.utarex.youngone.api.agency.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.utarex.youngone.api.agency.entity.AddUser;
import com.utarex.youngone.api.agency.entity.Agency;
import com.utarex.youngone.api.agency.entity.AgencyUserInfo;
import com.utarex.youngone.api.agency.entity.Emergency;
import com.utarex.youngone.api.agency.entity.EmergencyProcess;
import com.utarex.youngone.api.agency.entity.EmergencyVisit;
import com.utarex.youngone.api.agency.entity.History;
import com.utarex.youngone.api.agency.entity.Order;
import com.utarex.youngone.api.agency.entity.ProcessInfo;
import com.utarex.youngone.api.agency.entity.UserSearch;
import com.utarex.youngone.api.agency.entity.UserSearchResult;
import com.utarex.youngone.api.user.entity.Chpw;

@Mapper
public interface AgencyRepo {
	Agency getInfo(String id);
	int  changePw(Chpw chpw);
	String getUsrId(AgencyUserInfo aui);
	List<Emergency> getUserEmergency(String usrId);
	List<Emergency> getEmergency(AgencyUserInfo agncyId);
	List<History> getUserHistory(String usrId);
	int updVisit(EmergencyVisit ep);
	int updEmergency(EmergencyProcess ep);
	ProcessInfo getProcess(String id);
	List<Order> getOrderList(AgencyUserInfo aui);
	List<Order> getHistoryOrderList(AgencyUserInfo aui);
	List<UserSearchResult> doSearchUser(UserSearch us);
	int updDelivery(AgencyUserInfo aui);
	int addUser(AddUser agency);
}
