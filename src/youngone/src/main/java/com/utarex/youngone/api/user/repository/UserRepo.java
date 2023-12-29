package com.utarex.youngone.api.user.repository;

import java.util.List;

import com.utarex.youngone.api.agency.entity.Emergency;
import org.apache.ibatis.annotations.Mapper;

import com.utarex.youngone.api.user.entity.Chpw;
import com.utarex.youngone.api.user.entity.Command;
import com.utarex.youngone.api.user.entity.DeviceStatus;
import com.utarex.youngone.api.user.entity.Install;
import com.utarex.youngone.api.user.entity.OrderDtl;
import com.utarex.youngone.api.user.entity.OrderMst;
import com.utarex.youngone.api.user.entity.User;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepo {
	User getInfo(String id);
	int  changePw(Chpw chpw);
	int  insAction(Command cmd);
	DeviceStatus getDeviceStat(String usrId);
	Install getInstall(String install);
	int  insOrder(OrderMst mst);
	int  insOrderDtl(OrderDtl dtl);
	List<OrderDtl> lstOrderDtl(String id);
	int  insEmergency(String id, String code);
	List<Emergency> getEmergencyList(String id);
	String getUserIdFromMcId(String mcId);
}
