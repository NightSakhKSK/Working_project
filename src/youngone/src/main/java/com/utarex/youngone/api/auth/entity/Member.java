package com.utarex.youngone.api.auth.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Member implements UserDetails, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1276666242864153797L;
	@JsonProperty private String id;
    private String pw;
    @JsonProperty private String role;
    @JsonProperty private Date lastLoginDtti;
    @JsonProperty private Date firstRegDtti;
    @JsonProperty private Date lastUpdDtti;
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority("ROLE_" + this.getRole())); //SECURITY는 ROLE_을 PREFIX로 자동으로 붙여서 작동된다.
		return auth;
	}
	
	public String getRole() {
		return this.role;
	}
	
	@Override
	public String getPassword() {
		return this.pw;
	}
	@Override
	public String getUsername() {
		return this.id;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true; //계정만료여부
	}
	@Override
	public boolean isAccountNonLocked() {
		return true; //계정잠금여부
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true; //비밀번호 만료여부
	}
	@Override
	public boolean isEnabled() {
		return true; //계정활성화 여부
	}
}
