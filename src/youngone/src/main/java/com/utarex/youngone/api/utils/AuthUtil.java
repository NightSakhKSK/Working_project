package com.utarex.youngone.api.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.utarex.youngone.api.auth.entity.Member;

public class AuthUtil {
	
	//private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthUtil.class);
	
	public final static Member getMember() {
		Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
		Object mb = authenticationToken.getPrincipal();
		if(mb instanceof Member) {
			return (Member)mb;
		}
		return null;
	}
	
	public final static String getUserId() {
		Member ud = getMember();
		if(ud == null) return null;
		else return ud.getId();
	}
}
