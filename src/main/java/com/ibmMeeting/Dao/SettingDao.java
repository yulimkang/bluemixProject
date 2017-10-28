package com.ibmMeeting.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface SettingDao {
	
	void settingUpdate(HashMap<String,Object> settingValue); // 셋팅 수정
	HashMap<String,Object> settingLoad(); // 셋팅 목록
}