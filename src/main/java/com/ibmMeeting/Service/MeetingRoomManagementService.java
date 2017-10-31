package com.ibmMeeting.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.MeetingRoomDao;
import com.ibmMeeting.Dao.ReservationDao;
import com.ibmMeeting.VO.Conference;

@Service
public class MeetingRoomManagementService {
	
	@Autowired
	MeetingRoomDao meetingRoomDao;
	
	@Autowired
	ReservationDao reservationDao;
	
	
	/* 
	 * 작성자 : 박성준
	 * 회의실 리스트 출력
	 */
	public ArrayList<HashMap<String,Object>> meetingRoomList(){
		return meetingRoomDao.meetingRoomList();
	}
	
	/* 
	 * 작성자 : 박성준
	 * 회의실 추가
	 * 사용자가 선택한 회의실이름과 번호를 받아와 새롭게 추가
	 */
	public String meetingRoomAdd(HttpServletRequest request){
		
		String roomName = request.getParameter("addMeetingRoomName");
		Integer roomNumber = Integer.parseInt(request.getParameter("addMeetingRoomNumber"));

		HashMap<String,Object> meetingRoomAdd = new HashMap<String,Object>();
		meetingRoomAdd.put("roomName", roomName);
		meetingRoomAdd.put("roomNumber", roomNumber);
		meetingRoomDao.meetingRoomAdd(meetingRoomAdd);
		
		return ConstantCode.SUCCESS_STRING;
	}
	
	/* 
	 * 작성자 : 박성준
	 * 회의실 수정
	 * 사용자가 선택한 회의실이름과 번호, 그리고 시퀀스 번호를 받아와 업데이트
	 */
	public String meetingRoomUpdate(HttpServletRequest request){
		
		String roomName = request.getParameter("updateMeetingRoomName");
		Integer roomNumber = Integer.parseInt(request.getParameter("updateMeetingRoomNumber"));
		Integer roomSeq = Integer.parseInt(request.getParameter("updateMeetingRoomSeq"));

		HashMap<String,Object> meetingRoomUpdate = new HashMap<String,Object>();
		meetingRoomUpdate.put("roomName", roomName);
		meetingRoomUpdate.put("roomNumber", roomNumber);
		meetingRoomUpdate.put("roomSeq", roomSeq);
		meetingRoomDao.meetingRoomUpdate(meetingRoomUpdate);
		
		return ConstantCode.SUCCESS_STRING;
	}
	
	/* 
	 * 작성자 : 박성준
	 * 회의실 삭제
	 * 사용자가 선택한 회의실 시퀀스 번호로 삭제
	 */
	public String meetingRoomDelete(HttpServletRequest request){
		
		Integer meetingRoomSeq = Integer.parseInt(request.getParameter("meetingRoomSeq"));
		meetingRoomDao.meetingRoomDelete(meetingRoomSeq);
		reservationDao.deleteReservationByMeetingRoomSeq(meetingRoomSeq);
		return ConstantCode.SUCCESS_STRING;
	}
	
	/**
	 * 작성자 : 박세연
	 * DB에 등록된 회의실들 정보 가져오기
	 * @return
	 */
	public List<Conference> getResources(){
		
		List<Conference> list = meetingRoomDao.getResources();
		
		return list;
	}

}
