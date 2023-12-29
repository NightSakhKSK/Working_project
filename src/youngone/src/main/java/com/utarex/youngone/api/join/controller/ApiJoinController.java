package com.utarex.youngone.api.join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utarex.youngone.api.join.entity.AgencyJoin;
import com.utarex.youngone.api.join.entity.UserJoin;
import com.utarex.youngone.api.join.repository.JoinRepo;
import com.utarex.youngone.api.user.entity.CodeResponse;

@RestController
@RequestMapping("/api/join")
public class ApiJoinController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ApiJoinController.class);
	
	@Autowired
    JoinRepo joinRepo;
	
	@PostMapping("/checkid")
	public ResponseEntity<?> checkid(@RequestBody AgencyJoin inf) {
		if(inf == null) return ResponseEntity.badRequest().build();
		if(inf.getId() == null) return ResponseEntity.badRequest().build();
		if(inf.getId().length() < 1) return ResponseEntity.badRequest().build();
		log.debug("call checkid {}", inf.toString());
		int res = joinRepo.getCheckId(inf);
		if(res == 0) {
			return ResponseEntity.ok(new CodeResponse("0000"));	
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/agency")
	public ResponseEntity<?> agency(@RequestBody AgencyJoin join) {
		if(join == null) return ResponseEntity.badRequest().build();
		log.debug("call join/agency {}", join.toString());
		int res = joinRepo.joinAgency(join);
		if(res == 0) {
			return ResponseEntity.ok(new CodeResponse("1000"));	
		} else {
			return ResponseEntity.ok(new CodeResponse("0000"));	
		}
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> user(@RequestBody UserJoin join) {
		if(join == null) return ResponseEntity.badRequest().build();
		log.debug("call join/agency {}", join.toString());
		int res = joinRepo.joinUser(join);
		if(res == 0) {
			return ResponseEntity.ok(new CodeResponse("1000"));	
		} else {
			res = joinRepo.joinUserMc(join);
			if(res == 1) {
				return ResponseEntity.ok(new CodeResponse("0000"));
			}
			return ResponseEntity.ok(new CodeResponse("1001"));
		}
	}
}
