package com.ibmMeeting.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ibmMeeting.VO.Conference;



@Mapper
public interface MeetingRoomDao {
	
	/* 
	 * 작성자 : 박성준
	 * 회의실 관리
	 */
	
	ArrayList<HashMap<String,Object>> meetingRoomList();				// 회의실 리스트
	void meetingRoomAdd(HashMap<String,Object> meetingRoomAdd);			// 회의실 추가
	void meetingRoomUpdate(HashMap<String,Object> meetingRoomUpdate); 	// 회의실 수정
	void meetingRoomDelete(Integer meetingRoomSeq);						// 회의실 삭제
	String meetingRoomName(Integer confNo);
	/**
	 * 작성자 : 박세연
	 * DB에 등록된 회의실들 정보 가져오기
	 * @return
	 */
	List<Conference> getResources();
	
}