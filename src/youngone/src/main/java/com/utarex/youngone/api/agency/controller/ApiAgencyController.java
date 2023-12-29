package com.utarex.youngone.api.agency.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.utarex.youngone.api.agency.entity.AddUser;
import com.utarex.youngone.api.agency.entity.AddUserRes;
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
import com.utarex.youngone.api.agency.repository.AgencyRepo;
import com.utarex.youngone.api.user.entity.Chpw;
import com.utarex.youngone.api.user.entity.CodeResponse;
import com.utarex.youngone.api.user.service.UserService;
import com.utarex.youngone.api.utils.AuthUtil;
import com.utarex.youngone.api.utils.Const;
import com.utarex.youngone.api.utils.IssuedNumber;

@RestController
@RequestMapping("/api/agency")
public class ApiAgencyController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ApiAgencyController.class);
	
	@Autowired
    AgencyRepo agencyRepo;
	
	@Autowired
    UserService userService;
	
	@PostMapping("/info")
	public ResponseEntity<?> info() {
		log.debug("call agency info");
		Agency agency = agencyRepo.getInfo(AuthUtil.getUserId());
		return ResponseEntity.ok(agency);
	}
	
	@PostMapping("/chpw")
	public ResponseEntity<?> chpw(@RequestBody Chpw chpw) {
		if(chpw == null) {
			return ResponseEntity.ok(new CodeResponse("1009"));
		} else if(!chpw.getNewPw().equals(chpw.getConfirmPw())) {
			return ResponseEntity.ok(new CodeResponse("1001"));
		}
		chpw.setId(AuthUtil.getUserId());
		int res = agencyRepo.changePw(chpw);
		if(res == 1) {
			return ResponseEntity.ok(new CodeResponse("0000"));
		} else {
			return ResponseEntity.ok(new CodeResponse("1000"));
		}
	}
	
	@PostMapping("/process")
	public ResponseEntity<?> process() {
		log.debug("call agency process");
		ProcessInfo process = agencyRepo.getProcess(AuthUtil.getUserId());
		return ResponseEntity.ok(process);
	}
	
	@PostMapping("/user/order/delivery")
	public ResponseEntity<?> orderDelivery(@RequestBody AgencyUserInfo aui) {
		aui.setAgncyId(AuthUtil.getUserId());
		int res = agencyRepo.updDelivery(aui);
		if(res == 1) {
			return ResponseEntity.ok(new CodeResponse("0000"));	
		} else {
			return ResponseEntity.ok(new CodeResponse("1000"));	
		}
	}
	
	@PostMapping("/user/search")
	public ResponseEntity<?> userSearch(@RequestBody UserSearch us) {
		us.setAgncyId(AuthUtil.getUserId());
		List<UserSearchResult> usr = agencyRepo.doSearchUser(us);
		return ResponseEntity.ok(usr);
	}
	
	@PostMapping("/user/order/list")
	public ResponseEntity<?> orderList(@RequestBody AgencyUserInfo aui) {
		if(aui == null) aui = new AgencyUserInfo();
		aui.setAgncyId(AuthUtil.getUserId());
		List<Order> orders = agencyRepo.getOrderList(aui);
		
		List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
		int LastOdSeq = -1;
		Map<String, Object> cur = null;
		List<Map<String, Object>> curitems = null;
		for(Order od : orders) {
			if(od.getOrderSeq() != LastOdSeq) {
				LastOdSeq = od.getOrderSeq();
				cur =  new LinkedHashMap<String, Object>();
				lst.add(cur);
				cur.put("seq", od.getOrderSeq());
				cur.put("date", od.getOrderDtti());
				if(aui.getMcId() == null) cur.put("mcId", od.getMcId());
				cur.put("tp", od.getTp());
				curitems = new ArrayList<Map<String, Object>>();
				cur.put("items", curitems);
			}
			if(cur != null && curitems != null) {
				Map<String, Object> item = new LinkedHashMap<String, Object>();
				curitems.add(item);
				item.put("seq", od.getOrderDtlSeq());
				item.put("nm", Const.HashItemIdToName.get(od.getMdCtgrCd()));
				item.put("qty", od.getOrderQty());
			}
		}
		return ResponseEntity.ok(lst);
	}

	@PostMapping("/user/history/order/list")
	public ResponseEntity<?> historyOrderList(@RequestBody AgencyUserInfo aui) {
		if(aui == null) aui = new AgencyUserInfo();
		aui.setAgncyId(AuthUtil.getUserId());
		List<Order> orders = agencyRepo.getHistoryOrderList(aui);

		List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
		int LastOdSeq = -1;
		Map<String, Object> cur = null;
		List<Map<String, Object>> curitems = null;
		for(Order od : orders) {
			if(od.getOrderSeq() != LastOdSeq) {
				LastOdSeq = od.getOrderSeq();
				cur =  new LinkedHashMap<String, Object>();
				lst.add(cur);
				cur.put("seq", od.getOrderSeq());
				cur.put("date", od.getOrderDtti());
				if(aui.getMcId() == null) cur.put("mcId", od.getMcId());
				cur.put("tp", od.getTp());
				curitems = new ArrayList<Map<String, Object>>();
				cur.put("items", curitems);
			}
			if(cur != null && curitems != null) {
				Map<String, Object> item = new LinkedHashMap<String, Object>();
				curitems.add(item);
				item.put("seq", od.getOrderDtlSeq());
				item.put("nm", Const.HashItemIdToName.get(od.getMdCtgrCd()));
				item.put("qty", od.getOrderQty());
			}
		}
		return ResponseEntity.ok(lst);
	}
	
	@PostMapping("/order/list")
	public ResponseEntity<?> orderList() {
		return orderList(null);
	}
	
	@PostMapping("/user/info")
	public ResponseEntity<?> userInfo(@RequestBody AgencyUserInfo aui) {
		aui.setAgncyId(AuthUtil.getUserId());
		String usrId = agencyRepo.getUsrId(aui);
		if(usrId == null) return ResponseEntity.badRequest().build();
		return userService.info(usrId);
	}
	
	@PostMapping("/user/device/command")
	public ResponseEntity<?> command(@RequestBody AgencyUserInfo aui) {
		aui.setAgncyId(AuthUtil.getUserId());
		String usrId = agencyRepo.getUsrId(aui);
		if(usrId == null) return ResponseEntity.badRequest().build();
		aui.setUsrId(usrId);
		return userService.command(usrId, aui);
	}
	
	@PostMapping("/user/device/stat")
	public ResponseEntity<?> userDeviceStat(@RequestBody AgencyUserInfo aui) {
		aui.setAgncyId(AuthUtil.getUserId());
		String usrId = agencyRepo.getUsrId(aui);
		if(usrId == null) return ResponseEntity.badRequest().build();
		return userService.deviceStat(usrId);
	}
	
	@PostMapping("/user/emergency/list")
	public ResponseEntity<?> userEmergency(@RequestBody AgencyUserInfo aui) {
		aui.setAgncyId(AuthUtil.getUserId());
		String usrId = agencyRepo.getUsrId(aui);
		if(usrId == null) return ResponseEntity.badRequest().build();
		List<Emergency> emergency = agencyRepo.getUserEmergency(usrId);
		return ResponseEntity.ok(emergency);
	}
	
	@PostMapping("/user/history/list")
	public ResponseEntity<?> userHistory(@RequestBody AgencyUserInfo aui) {
		aui.setAgncyId(AuthUtil.getUserId());
		String usrId = agencyRepo.getUsrId(aui);
//		if(usrId == null) return ResponseEntity.badRequest().build();
		List<History> history = agencyRepo.getUserHistory(usrId);
		return ResponseEntity.ok(history);
	}

	@PostMapping("/emergency/list")
	public ResponseEntity<?> emergencyList(@RequestBody AgencyUserInfo aui) {
		aui.setAgncyId(AuthUtil.getUserId());
		List<Emergency> emergency = agencyRepo.getEmergency(aui);
		return ResponseEntity.ok(emergency);
	}
	
	@PostMapping("/emergency/process")
	public ResponseEntity<?> emergencyProcess(@RequestBody EmergencyProcess ep) {
		ep.setAgncyId(AuthUtil.getUserId());
		int res = agencyRepo.updEmergency(ep);
		if(res == 1) {
			return ResponseEntity.ok(new CodeResponse("0000"));	
		} else {
			return ResponseEntity.ok(new CodeResponse("1000"));	
		}
	}
	
	@PostMapping("/emergency/visit")
	public ResponseEntity<?> emergencyVisit(@RequestBody EmergencyVisit ep) {
		ep.setAgncyId(AuthUtil.getUserId());
		int res = agencyRepo.updVisit(ep);
		if(res == 1) {
			return ResponseEntity.ok(new CodeResponse("0000"));	
		} else {
			return ResponseEntity.ok(new CodeResponse("1000"));	
		}
	}
	
	@PostMapping("/user/add")
	public ResponseEntity<?> userAdd(@RequestBody AddUser ua) {
		//Agency agency = agencyRepo.getInfo(AuthUtil.getUserId());
		if(ua == null) return ResponseEntity.badRequest().build();
		log.debug("call userAdd {}", ua.toString());
		if(ua.getMcId() == null) return ResponseEntity.badRequest().build();
		if(ua.getTelNo() != null) {
			ua.setTelNo(ua.getTelNo().replaceAll("[^0-9]", ""));
		}
		ua.setTempIssdNo(IssuedNumber.Get(6));
		ua.setAgncyId(AuthUtil.getUserId());
		log.debug("info {}", (new Gson()).toJson(ua));
		int res = agencyRepo.addUser(ua);
		if(res != 1) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(new AddUserRes(ua.getTempIssdNo()));
	}
}
