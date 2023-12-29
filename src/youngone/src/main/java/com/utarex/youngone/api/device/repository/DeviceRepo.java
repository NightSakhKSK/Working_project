package com.utarex.youngone.api.device.repository;

import org.apache.ibatis.annotations.Mapper;

import com.utarex.youngone.api.device.entity.DeviceStatusIoT;

@Mapper
public interface DeviceRepo {
	int  pushInfo(DeviceStatusIoT iot);
}
