package com.ibmMeeting.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface SettingDao {
	
	void settingUpdate(HashMap<String,Object> settingValue);
	HashMap<String,Object> settingLoad();
}