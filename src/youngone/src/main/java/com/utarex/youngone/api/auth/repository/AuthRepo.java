package com.utarex.youngone.api.auth.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.utarex.youngone.api.auth.entity.Member;

@Mapper
public interface AuthRepo {
	Member getMember(String id);
	Member getMemberByIdAndPassword(String id, String pw);
	List<Member> lstMembers();
}
