package com.ibmMeeting.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ibmMeeting.VO.Reservation;

@Mapper
public interface ReservationDao {

	List<Reservation> getReservation();
	
	List<Reservation> getReservationInfo(int no);
	
	void registReservation(Reservation reservation);
	
	String getPwd(int rsvNo);
	
	void modifyReservation(Reservation reservation);
	
	void deleteReservation(int rsvNo);
	
	void modifyRsvByDrop(HashMap<String, Object> map);
	
	int preventMonopoly(HashMap<String, Object> map);
	
	List<Reservation> controlStartTime(HashMap<String, Object> map);

	int chkRsvedTime(HashMap<String, Object> map);
	
	void modifyRsvByResize(HashMap<String, Object> map);
	
	String getRsvedMemPn(int rsvNo);
	
	String getRsvConfirmStateVal(int rsvNo);
	
	
	

	void deleteReservationByDate(String deleteDate);
	
	
	/* 
	 * 작성자 : 박성준
	 * 예약 부가적 dao
	 */
	ArrayList<HashMap<String,Object>> allReservationList(String wantDate);	// 특정날짜 예약 목록
	void deleteReservationByMeetingRoomSeq(Integer meetingRoomSeq);			// 회의실에 연결된 예약삭제
	Integer similarTitleCheck(String rsvTitle);								// 제목 비슷한 내용체크
	
}
