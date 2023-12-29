package com.utarex.youngone.api.corp.controller;

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
import com.utarex.youngone.api.agency.entity.Agency;
import com.utarex.youngone.api.agency.entity.AgencyUserInfo;
import com.utarex.youngone.api.agency.entity.Emergency;
import com.utarex.youngone.api.agency.entity.Order;
import com.utarex.youngone.api.agency.entity.ProcessInfo;
import com.utarex.youngone.api.agency.entity.UserSearch;
import com.utarex.youngone.api.agency.entity.UserSearchResult;
import com.utarex.youngone.api.agency.repository.AgencyRepo;
import com.utarex.youngone.api.corp.entity.AddAgency;
import com.utarex.youngone.api.corp.entity.AddAgencyRes;
import com.utarex.youngone.api.corp.entity.AgencyUserSearch;
import com.utarex.youngone.api.corp.entity.Corp;
import com.utarex.youngone.api.corp.entity.Distribute;
import com.utarex.youngone.api.corp.repository.CorpRepo;
import com.utarex.youngone.api.user.entity.Chpw;
import com.utarex.youngone.api.user.entity.CodeResponse;
import com.utarex.youngone.api.utils.AuthUtil;
import com.utarex.youngone.api.utils.Const;
import com.utarex.youngone.api.utils.IssuedNumber;

@RestController
@RequestMapping("/api/corp")
public class ApiCorpController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ApiCorpController.class);
	
	@Autowired
    CorpRepo corpRepo;
	
	@PostMapping("/info")
	public ResponseEntity<?> info() {
		log.debug("call corp info");
		Corp corp = corpRepo.getInfo();
		return ResponseEntity.ok(corp);
	}
	
	@Autowired
    AgencyRepo agencyRepo;
	
	@PostMapping("/agency/info")
	public ResponseEntity<?> agencyInfo(@RequestBody AgencyUserInfo aui) {
		log.debug("call corp agency info");
		Agency agency = agencyRepo.getInfo(aui.getAgncyId());
		return ResponseEntity.ok(agency);
	}
	
	@PostMapping("/agency/process")
	public ResponseEntity<?> agencyProcess(@RequestBody AgencyUserInfo aui) {
		log.debug("call corp agency process");
		ProcessInfo process = agencyRepo.getProcess(aui.getAgncyId());
		return ResponseEntity.ok(process);
	}
	
	@PostMapping("/process")
	public ResponseEntity<?> process() {
		log.debug("call corp process");
		ProcessInfo process = corpRepo.getProcess();
		return ResponseEntity.ok(process);
	}
	
	@PostMapping("/distribute")
	public ResponseEntity<?> distribute() {
		log.debug("call corp distribute");
		List<Distribute> distribute = corpRepo.getDistribute();
		return ResponseEntity.ok(distribute);
	}
	
	@PostMapping("/chpw")
	public ResponseEntity<?> chpw(@RequestBody Chpw chpw) {
		if(chpw == null) {
			return ResponseEntity.ok(new CodeResponse("1009"));
		} else if(!chpw.getNewPw().equals(chpw.getConfirmPw())) {
			return ResponseEntity.ok(new CodeResponse("1001"));
		}
		chpw.setId(AuthUtil.getUserId());
		int res = corpRepo.changePw(chpw);
		if(res == 1) {
			return ResponseEntity.ok(new CodeResponse("0000"));
		} else {
			return ResponseEntity.ok(new CodeResponse("1000"));
		}
	}
	
	@PostMapping("/agency/add")
	public ResponseEntity<?> agencyAdd(@RequestBody AddAgency info) {
		log.debug("/api/corp/agency/add {}", (new Gson()).toJson(info));
		if(info == null) return ResponseEntity.badRequest().build();
		if(info.getBsnLcnNo() == null) return ResponseEntity.badRequest().build();
		info.setBsnLcnNo(info.getBsnLcnNo().replaceAll("[^0-9]", ""));
		if(info.getBsnLcnNo().length() != 10) return ResponseEntity.badRequest().build();
		if(info.getTelNo() != null) {
			info.setTelNo(info.getTelNo().replaceAll("[^0-9]", ""));
		}
		info.setTempIssdNo(IssuedNumber.Get(6));
		info.setAdmrId(AuthUtil.getUserId());
		log.debug("info {}", (new Gson()).toJson(info));
		int res = corpRepo.addAgency(info);
		if(res != 1) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(new AddAgencyRes(info.getTempIssdNo()));
	}
	
	@PostMapping("/agency/order/list")
	public ResponseEntity<?> orderList(@RequestBody AgencyUserInfo aui) {
		List<Order> orders = corpRepo.getOrderList(aui);
		
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
	
	@PostMapping("/agency/order/today")
	public ResponseEntity<?> orderToday(@RequestBody AgencyUserInfo aui) {
		aui.setToday(true);
		return orderList(aui);
	}
	
	@PostMapping("/agency/emergency/list")
	public ResponseEntity<?> emergencyList(@RequestBody AgencyUserInfo aui) {
		List<Emergency> emergencies = corpRepo.getEmergency(aui);
		return ResponseEntity.ok(emergencies);
	}
	
	@PostMapping("/agency/emergency/today")
	public ResponseEntity<?> emergencyToday(@RequestBody AgencyUserInfo aui) {
		aui.setToday(true);
		return emergencyList(aui);
	}
	
	@PostMapping("/user/search")
	public ResponseEntity<?> userSearch(@RequestBody AgencyUserSearch aus) {
		UserSearch us = new UserSearch();
		us.setAgncyId(aus.getAgncyId());
		us.setText(aus.getText());
		List<UserSearchResult> usr = agencyRepo.doSearchUser(us);
		return ResponseEntity.ok(usr);
	}	
}
