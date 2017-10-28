package com.ibmMeeting.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TestDao {
	
	String test();
	
	void updateNames();
	void updateClient();
	void updateConnection();
	void updateDatabase();
	void updateResults();
	void updateServer();
	void updateSystem();
	
	ArrayList<HashMap<String,Object>> getSystemInfo();
}