package com.utarex.youngone.api.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utarex.youngone.api.user.entity.Chpw;
import com.utarex.youngone.api.user.entity.Command;
import com.utarex.youngone.api.user.entity.OrderDtl;
import com.utarex.youngone.api.user.service.UserService;
import com.utarex.youngone.api.utils.AuthUtil;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	UserService user;
	
	@PostMapping("/info")
	public ResponseEntity<?> info() {
		return user.info(AuthUtil.getUserId());
	}
	
	@PostMapping("/install")
	public ResponseEntity<?> install() {
		return user.install(AuthUtil.getUserId());
	}
	
	@PostMapping("/chpw")
	public ResponseEntity<?> chpw(@RequestBody Chpw chpw) {
		return user.chpw(AuthUtil.getUserId(), chpw);
	}
	
	@PostMapping("/device/command")
	public ResponseEntity<?> command(@RequestBody Command cmd) {
		return user.command(AuthUtil.getUserId(), cmd);
	}
	
	@PostMapping("/device/stat")
	public ResponseEntity<?> deviceStat() {
		return user.deviceStat(AuthUtil.getUserId());
	}
	
	@PostMapping("/order/list")
	public ResponseEntity<?> orderList() {
		return user.orderList(AuthUtil.getUserId());
	}
	@PostMapping("/emergency")
	public ResponseEntity<?> emergency() {
		return user.emergency(AuthUtil.getUserId());
	}
	@PostMapping("/emergency/list")
	public ResponseEntity<?> emergencyList() {
		return user.emergencyList(AuthUtil.getUserId());
	}

	@PostMapping("/order")
	public ResponseEntity<?> order(@RequestBody List<OrderDtl> orders) {
		return user.order(AuthUtil.getUserId(), orders);
	}
}
