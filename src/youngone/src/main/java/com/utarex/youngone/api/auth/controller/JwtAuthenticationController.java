package com.utarex.youngone.api.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.utarex.youngone.api.auth.entity.JwtRequest;
import com.utarex.youngone.api.auth.entity.JwtResponse;
import com.utarex.youngone.api.auth.entity.Member;
import com.utarex.youngone.api.auth.repository.AuthRepo;
import com.utarex.youngone.config.auth.JwtTokenUtil;

@RestController
@CrossOrigin
public class   JwtAuthenticationController {
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    AuthRepo loginRepo;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest req) throws Exception {
        final Member member = loginRepo.getMemberByIdAndPassword(req.getId(), req.getPw());
        if(member == null) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final String token = jwtTokenUtil.generateToken(member.getUsername(), member.getRole());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
