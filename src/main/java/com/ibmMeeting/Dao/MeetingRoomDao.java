package com.ibmMeeting.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface MeetingRoomDao {
	ArrayList<HashMap<String,Object>> meetingRoomList();
	void meetingRoomAdd(HashMap<String,Object> meetingRoomAdd);
	void meetingRoomUpdate(HashMap<String,Object> meetingRoomUpdate);
	void meetingRoomDelete(Integer meetingRoomSeq);
}