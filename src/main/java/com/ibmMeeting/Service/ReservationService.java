package com.ibmMeeting.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.AdminDao;
import com.ibmMeeting.Dao.MeetingRoomDao;
import com.ibmMeeting.Dao.ReservationDao;
import com.ibmMeeting.VO.Reservation;

@Service
public class ReservationService {

	@Autowired
	ReservationDao reservationDao;
	
	@Autowired
	MeetingRoomDao meetingRoomDao;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	AdminDao adminDao;
	
	/**
	 * 작성자 : 박세연
	 * 등록된 예약내역 간단 조회
	 * @return
	 */
	public List<Reservation> getReservation(){
		
		List<Reservation> list = reservationDao.getReservation();
		
		return list;
	}
	
	/**
	 * 작성자 : 박세연
	 * 등록된 예약내역 상세 조회
	 * @param no
	 * @return
	 */
	public List<Reservation> getReservationInfo(int no){
		
		List<Reservation> list = reservationDao.getReservationInfo(no);
		
		return list;
	}
	
	/**
	 * 작성자 : 박세연
	 * 예약 등록
	 * @param reservation
	 * @param emailCheckValue
	 * @throws MessagingException 
	 */
	public void registReservation(Reservation reservation, String emailCheckValue) throws MessagingException{
		
		//객체안 값 설정
		Date date = new Date();
		reservation.setRsvNo(0);
		reservation.setRsvRegDate(date);
		reservation.setRsvEmailCheck(emailCheckValue);
		reservation.setRsvRepeatPeriod("N");
		reservation.setRsvSetting("0");
		reservation.setRsvRepeatNo(0);
		reservation.setRsvDescription("");
	
		//만약 key값을 이용해서 사이트명을 구분한다면 바뀌어야 할 코드
		reservation.setRsvComp(ConstantCode.COMPANY_NAME);
		
		String email = adminDao.getAdminEmail();
		String subject = "[IBM회의실] " + reservation.getRsvMemNm() + "님이 가예약을 신청하셨습니다.";
		String contentTitle = "회의제목 : " + reservation.getRsvTitle();
		String contentTime = "회의시간 : " + reservation.getRsvStartTime() + " : " + reservation.getRsvEndTime();
		String contentURL = ConstantCode.URL;	
		String content = contentTitle + "\n" + contentTime +"\n" +contentURL;
		
		if(reservation.getRsvConfirmState()=="N"){
			commonService.sendEmail(email, subject, content);
		}
		
		//insert
		reservationDao.registReservation(reservation);
		
	}
	
	/**
	 * 작성자 : 박세연
	 * 비밀번호 확인을 위해 등록된 각 예약NO의 비밀번호 가져오기
	 * @param rsvNo
	 * @return
	 */
	public String getPwd(int rsvNo){
		
		String pwd = reservationDao.getPwd(rsvNo);
		
		return pwd;
	}
	
	/**
	 * 작성자 : 박세연
	 * 예약 수정하기
	 * @param reservation
	 * @param emailCheckValue
	 */
	public void modifyReservation(Reservation reservation, String emailCheckValue){
		
		//객체안 값 설정
		reservation.setRsvEmailCheck(emailCheckValue);

		//modify
		reservationDao.modifyReservation(reservation);
				
	}
	
	/**
	 * 작성자 : 박세연
	 * 예약 삭제하기
	 * @param rsvNo
	 */
	public void deleteReservation(int rsvNo){
		
		reservationDao.deleteReservation(rsvNo);
	}
	
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 예약내역 중 입력날짜 이후의 값을 삭제
	 * @param request
	 * @return
	 */
	public Integer deleteReservationByDate(HttpServletRequest request) {
		
		String deleteDate = request.getParameter("deleteDate");
		
		reservationDao.deleteReservationByDate(deleteDate);

		
		return ConstantCode.SUCCESS;
	}
	
	/**
	 * 작성자 : 박세연
	 * 드래그 앤 드롭을 통해 예약 수정하기
	 * @param rsvNo
	 * @param rsvConfNo
	 * @param rsvStartTime
	 * @param rsvEndTime
	 */
	public void modifyRsvByDrop(int rsvNo, int rsvConfNo, Time rsvStartTime, Time rsvEndTime, Time rsvTotalTime){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rsvNo", rsvNo);
		map.put("rsvConfNo", rsvConfNo);
		map.put("rsvStartTime", rsvStartTime);
		map.put("rsvEndTime", rsvEndTime);
		map.put("rsvTotalTime", rsvTotalTime);
		
		reservationDao.modifyRsvByDrop(map);
	}
	
	/**
	 * 작성자 : 박세연
	 * 회의실 독점 방지를 위해 한 주의 예약내역 개수세기
	 * @param rsvTitle
	 * @param rsvMemPn
	 * @return
	 */
	public int preventMonopoly(String rsvTitle, String rsvMemPn){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rsvTitle", rsvTitle);
		map.put("rsvMemPn", rsvMemPn);
		map.put("rsvMaxTime", ConstantCode.RESERVATION_MAX_TIME);
		
		int count = reservationDao.preventMonopoly(map);

		return count;
	}
	
	
	/**
	 * 작성자 : 박세연
	 * 이미 예약된 시작&종료시간 select option disabled 시키기
	 * @param rsvDate
	 * @param rsvConfNo
	 * @return
	 */
	public List<Reservation> controlStartTime(String rsvDate, int rsvConfNo){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rsvDate", rsvDate);
		map.put("rsvConfNo", rsvConfNo);
		List<Reservation> rsvList = reservationDao.controlStartTime(map);

		return rsvList;
	}
	
	/**
	 * 작성자 : 박세연
	 * 이미 예약된 시간선택 후 등록/수정 진행시, return false시키기 위한 메소드
	 * @param rsvNo
	 * @param rsvDate
	 * @param rsvConfNo
	 * @param rsvStartTime
	 * @param rsvEndTime
	 * @return
	 */
	public int chkRsvedTime(int rsvNo, String rsvDate, int rsvConfNo, Time rsvStartTime, Time rsvEndTime){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rsvNo", rsvNo);
		map.put("rsvDate", rsvDate);
		map.put("rsvConfNo", rsvConfNo);
		map.put("rsvStartTime", rsvStartTime);
		map.put("rsvEndTime", rsvEndTime);
		int count = reservationDao.chkRsvedTime(map);
		
		return count;
	}
	
	// ParkSungJun - reservationList
	public ArrayList<HashMap<String,Object>> allReservationList(HttpServletRequest request){
		
		String wantDate;
		if(request.getParameter("datepicker")!=null){
			wantDate=request.getParameter("datepicker");
		}
		else{
			wantDate = commonService.nowTime();
		}
		
		return reservationDao.allReservationList(wantDate);
		
	}
	
	/**
	 * 작성자 : 박세연
	 * resize 통해 예약 수정하기
	 * @param rsvNo
	 * @param rsvStartTime
	 * @param rsvEndTime
	 * @param rsvTotalTime
	 * @param rsvConfirmState
	 */
	public void modifyRsvByResize(int rsvNo, Time rsvStartTime, Time rsvEndTime, Time rsvTotalTime, String rsvConfirmState){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rsvNo", rsvNo);
		map.put("rsvStartTime", rsvStartTime);
		map.put("rsvEndTime", rsvEndTime);
		map.put("rsvTotalTime", rsvTotalTime);
		map.put("rsvConfirmState", rsvConfirmState);
		
		reservationDao.modifyRsvByResize(map);
	}
	
	/**
	 * 작성자 : 박세연
	 * 예약내역의 예약자 전화번호(PK) 가져오기
	 * @param rsvNo
	 * @return
	 */
	public String getRsvedMemPn(int rsvNo){
	
		String pn = reservationDao.getRsvedMemPn(rsvNo);
		
		return pn;
	}
	
	
	/**
	 * 작성자 : 박성준
	 * 예약 번호를 통해 예약내역의 정보를 받아와 사용자에게 비밀번호를 전송
	 * @throws MessagingException 
	 */
	public void emailFind(int rsvNo) throws MessagingException{
		
		List<Reservation> reservationInfo = reservationDao.getReservationInfo(rsvNo);
		
		String rsvMemberEmail = reservationInfo.get(ConstantCode.ZERO).getRsvMemEm();
		String memberName = reservationInfo.get(ConstantCode.ZERO).getRsvMemNm();
		String reservationTitle = reservationInfo.get(ConstantCode.ZERO).getRsvTitle();
		String reservationPw = reservationInfo.get(ConstantCode.ZERO).getRsvDelPwd();
		
		String subject = "[IBM 회의실] " + memberName+ "님 비밀번호 확인부탁드립니다";
		String contentTitle = "회의 :" + reservationTitle;
		String contentMain = "비밀번호 : " + reservationPw;
		String contentURL = ConstantCode.URL;
		String contentTotal = contentTitle+"\n"+ contentMain+"\n"+ contentURL;
		
		commonService.sendEmail(rsvMemberEmail, subject, contentTotal);
		
	}
	
	/**
	 * 작성자 : 박세연
	 * 가예약 승인된 예약은 관리자만 수정 가능
	 * @param rsvNo
	 * @return
	 */
	public String getRsvConfirmStateVal(int rsvNo){
	
		String state = reservationDao.getRsvConfirmStateVal(rsvNo);
		
		return state;
	}
	
	/**
	 * 작성자 : 박세연
	 * 등록된 예약 위로 마우스 위치시, 예약자 내역 tooltip에 띄우기
	 * @param rsvNo
	 * @return
	 */
	public List<Reservation> showInfoByTooltip(int rsvNo){
		
		List<Reservation> list = reservationDao.showInfoByTooltip(rsvNo);
		
		return list;
	}
	
	
}
