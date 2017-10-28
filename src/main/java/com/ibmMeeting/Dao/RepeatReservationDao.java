package com.ibmMeeting.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface RepeatReservationDao {
	
	/* 
	 * 작성자 : 박성준
	 * 반복예약신청
	 */
	
	Integer repeatCheck(HashMap<String,Object> repeatInformation);				// 반복예약 가능여부 체크
	void repeatReservationSubmit(HashMap<String,Object> repeatInformation);		// 반복예약 신청
	Integer repeatSeqMaxValue();												// 반복예약 seq 가장 큰 값

}