package com.ibmMeeting.Dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardingDao {
	
	ArrayList<HashMap<String,Object>> BoardingListAll();
	ArrayList<HashMap<String,Object>> BoardingListName();
	ArrayList<HashMap<String,Object>> BoardingListNumber();

	

}
