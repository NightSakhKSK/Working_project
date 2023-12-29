package com.utarex.youngone.api.device.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.utarex.youngone.api.device.entity.DeviceStatusIoT;
import com.utarex.youngone.api.device.repository.DeviceRepo;
import com.utarex.youngone.api.user.entity.CodeResponse;

@RestController
@RequestMapping("/api/device")
public class ApiDeviceController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ApiDeviceController.class);
	
	@Autowired
    DeviceRepo deviceRepo;
	
	@PostMapping("/iot")
	public ResponseEntity<?> info(@RequestBody DeviceStatusIoT iot) {
		log.debug("call device push" + (new Gson()).toJson(iot));
		int res = deviceRepo.pushInfo(iot);
		if(res == 1 || res == 2) return ResponseEntity.ok(new CodeResponse("0000")); // 1== insert 2 == update
		return ResponseEntity.badRequest().build();
	}
}
