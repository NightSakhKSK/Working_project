package com.utarex.youngone.api.user.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.utarex.youngone.api.agency.entity.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.utarex.youngone.api.user.entity.Chpw;
import com.utarex.youngone.api.user.entity.CodeResponse;
import com.utarex.youngone.api.user.entity.Command;
import com.utarex.youngone.api.user.entity.DeviceStatus;
import com.utarex.youngone.api.user.entity.Install;
import com.utarex.youngone.api.user.entity.OrderDtl;
import com.utarex.youngone.api.user.entity.OrderMst;
import com.utarex.youngone.api.user.entity.User;
import com.utarex.youngone.api.user.repository.UserRepo;
import com.utarex.youngone.api.utils.Const;

@Service
public class UserService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

	ArrayList<Command> commandToPublish = new ArrayList<>();

	public ArrayList<Command> getCommandToPublish() {
		return commandToPublish;
	}

	@Autowired
    UserRepo userRepo;
	
	public ResponseEntity<?> info(String usrId) {
		User user = userRepo.getInfo(usrId);
		return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<?> install(String usrId) {
		Install ins = userRepo.getInstall(usrId);
		return ResponseEntity.ok(ins);
	}
	
	public ResponseEntity<?> chpw(String usrId, Chpw chpw) {
		if(chpw == null) {
			return ResponseEntity.ok(new CodeResponse("1009"));
		} else if(!chpw.getNewPw().equals(chpw.getConfirmPw())) {
			return ResponseEntity.ok(new CodeResponse("1001"));
		}
		chpw.setId(usrId);
		int res = userRepo.changePw(chpw);
		if(res == 1) {
			return ResponseEntity.ok(new CodeResponse("0000"));
		} else {
			return ResponseEntity.ok(new CodeResponse("1000"));
		}
	}
	
	public ResponseEntity<?> command(String usrId, Command cmd) {
		if(cmd == null) {
			return ResponseEntity.ok(new CodeResponse("1009"));
		}
		cmd.setUsrId(usrId);

		commandToPublish.add(cmd);

		return ResponseEntity.ok(new CodeResponse("0000"));
//		int res = userRepo.insAction(cmd);
//		if(res == 1) {
//			return ResponseEntity.ok(new CodeResponse("0000"));
//		} else {
//			return ResponseEntity.ok(new CodeResponse("1000"));
//		}
	}
	
	public ResponseEntity<?> deviceStat(String usrId) {
		DeviceStatus res = userRepo.getDeviceStat(usrId);
		return ResponseEntity.ok(res);
	}
	
	public ResponseEntity<?> orderList(String usrId) {
		List<OrderDtl> orders = userRepo.lstOrderDtl(usrId);
		List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
		int LastOdSeq = -1;
		Map<String, Object> cur = null;
		List<Map<String, Object>> curitems = null;
		for(OrderDtl od : orders) {
			if(od.getOrderSeq() != LastOdSeq) {
				LastOdSeq = od.getOrderSeq();
				cur =  new LinkedHashMap<String, Object>();
				lst.add(cur);
				cur.put("seq", od.getOrderSeq());
				cur.put("date", od.getOrderDt());
				curitems = new ArrayList<Map<String, Object>>();
				cur.put("items", curitems);
			}
			if(cur != null && curitems != null) {
				Map<String, Object> item = new LinkedHashMap<String, Object>();
				curitems.add(item);
				item.put("seq", od.getOrderDtlSeq());
				item.put("nm", Const.HashItemIdToName.get(od.getId()));
				item.put("qty", od.getValue());
			}
		}
		return ResponseEntity.ok(lst);
	}

	public ResponseEntity<?> emergency(String usrId) {
		int res = userRepo.insEmergency(usrId, "USER_CALL");
		if(res == 0) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(new CodeResponse("0000"));
	}
	public ResponseEntity<?> emergencyList(String usrId) {
		List<Emergency> emergency = userRepo.getEmergencyList(usrId);
		return ResponseEntity.ok(emergency);
	}
	public ResponseEntity<?> order(String usrId, List<OrderDtl> orders) {
		OrderMst om = new OrderMst();
		om.setUsrId(usrId);
		int res = userRepo.insOrder(om);
		if(res == 0) return ResponseEntity.badRequest().build();
		for(int i=0;i<orders.size();i++) {
			OrderDtl od = orders.get(i);
			od.setOrderSeq(om.getOrderSeq());
			od.setOrderDtlSeq(i+1);
			od.setUsrId(om.getUsrId());
			res = userRepo.insOrderDtl(od);
			if(res == 0) return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(new CodeResponse("0000"));
	}
}
