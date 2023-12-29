package com.utarex.youngone.api.join.repository;

import org.apache.ibatis.annotations.Mapper;

import com.utarex.youngone.api.join.entity.AgencyJoin;
import com.utarex.youngone.api.join.entity.UserJoin;

@Mapper
public interface JoinRepo {
	int getCheckId(AgencyJoin join);
	int joinAgency(AgencyJoin join);
	int joinUser(UserJoin join);
	int joinUserMc(UserJoin join);
}
