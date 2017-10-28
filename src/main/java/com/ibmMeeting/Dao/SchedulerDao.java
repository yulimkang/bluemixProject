package com.ibmMeeting.Dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulerDao {
	
	/* 
	 * 작성자 : 박성준
	 * 스케쥴러
	 */
	
	Integer emailSendUserCount(HashMap<String,Object> dateInformation);		// 이메일 보내줘야 하는 유저 수
	ArrayList<HashMap<String,Object>> reservaionEmailInfo(HashMap<String,Object> dateInformation); // 특정날짜에 메일을 보내야 하는 이메일 및 유저 리스트
	void eMailSendStateUpdate(Integer rsvNumber); // 이메일 보낸 후 이메일 상태 변경
}