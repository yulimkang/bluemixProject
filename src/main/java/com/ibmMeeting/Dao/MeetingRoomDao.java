package com.ibmMeeting.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ibmMeeting.VO.Conference;

@Mapper
public interface MeetingRoomDao {
	ArrayList<HashMap<String,Object>> meetingRoomList();
	void meetingRoomAdd(HashMap<String,Object> meetingRoomAdd);
	void meetingRoomUpdate(HashMap<String,Object> meetingRoomUpdate);
	void meetingRoomDelete(Integer meetingRoomSeq);
	
	List<Conference> getResources();
}