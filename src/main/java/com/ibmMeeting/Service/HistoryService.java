package com.ibmMeeting.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.HistoryDao;
import com.ibmMeeting.Dao.MeetingRoomDao;
import com.ibmMeeting.Dao.ReservationDao;
import com.ibmMeeting.VO.History;
import com.ibmMeeting.VO.Reservation;

@Service
public class HistoryService {
	
	@Autowired
	HistoryDao historyDao;
	
	@Autowired
	MeetingRoomDao meetingRoomDao;
	
	@Autowired
	ReservationDao reservationDao;
	
	@Autowired
	CommonService commonService;
	
	
	

	/**
	 * 작성자 : 박세연
	 * 예약기록에 등록하기 - 등록/수정/삭제
	 * @param reservation
	 * @param hstState
	 */
	public void insertHistory(Reservation reservation, String hstState){

		History history = new History();
		Date date = new Date();
		
		//만약 key값을 이용해서 사이트명을 구분한다면 바뀌어야 할 코드
		history.setHstRsvComp(ConstantCode.COMPANY_NAME);
		
		history.setHstRsvTitle(reservation.getRsvTitle());
		history.setHstDate(reservation.getRsvDate());
		history.setHstTotalTime(reservation.getRsvTotalTime());
		history.setHstStartTime(reservation.getRsvStartTime());
		history.setHstEndTime(reservation.getRsvEndTime());
		history.setHstRsvMemNm(reservation.getRsvMemNm());
		history.setHstRsvMemPn(reservation.getRsvMemPn());
		history.setHstRsvMemEm(reservation.getRsvMemEm());
		history.setHstDelPwd(reservation.getRsvDelPwd());
		history.setHstRegDate(date);
		history.setHstConfNo(reservation.getRsvConfNo());
		history.setHstRepeatPeriod("N");
		history.setHstSetting("0");
		history.setHstRsvState(hstState); // RESERVE/MODIFY/DELETE
		history.setHstRepeatNo(0);
		history.setHstDescription("");
		
		historyDao.registHistory(history);
		
	}
	
	/* 
	 * 작성자 : 고창환
	 * 가예약 리스트
	 */
	public ArrayList<HashMap<String,Object>> fakeReservation() {
		// TODO Auto-generated method stub
		return historyDao.fakeReservation();
	}
	/* 
	 * 작성자 : 고창환
	 * 가예약 승인 기능(승인 전 히스토리테이블 인서트)
	 */
	public String reservationUpdate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String reservationNumber=request.getParameter("reservationSeq");
		String repeatNo=request.getParameter("repeatNo");
		
		HashMap<String,Object> reservationUpdateAndInsert = new HashMap<String,Object>();
		
		reservationUpdateAndInsert.put("reservationNo", reservationNumber);
		reservationUpdateAndInsert.put("repeatNo",repeatNo);

		historyDao.reservationUpdateAndInsert(reservationUpdateAndInsert);
		
		HashMap<String,Object> reservationUpdate = new HashMap<String,Object>();
		
		reservationUpdate.put("reservationNo", reservationNumber);
		reservationUpdate.put("repeatNo",repeatNo);
		
		historyDao.reservationUpdate(reservationUpdate);
		
		
		/*
		 * 가예약 및 반복예약 승인 메일
		 * 작성자 : 박성준
		 */
		
		Integer reservationNo = Integer.parseInt(reservationNumber);
		List<Reservation> reservation = reservationDao.getReservationInfo(reservationNo);
		
		String email = reservation.get(ConstantCode.ZERO).getRsvMemEm();
		String subject = "[IBM회의실] " + reservation.get(ConstantCode.ZERO).getRsvMemNm() + "님 예약이 승인됐습니다.";
		String contentTitle = "회의제목 : " + reservation.get(ConstantCode.ZERO).getRsvTitle();
		String contentTime = "회의시간 : " + reservation.get(ConstantCode.ZERO).getRsvStartTime() + " : " + reservation.get(ConstantCode.ZERO).getRsvEndTime();
		String contentURL = ConstantCode.URL;	
		String content = contentTitle + "\n" + contentTime +"\n" +contentURL;
		
		commonService.sendEmail(email, subject, content);
		
		return "success";
		
	}
	/* 
	 * 작성자 : 고창환
	 * 가예약 반려 기능(반려전 히스토리 인서트)
	 */
	public String reservationDelete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String reservationNumber=request.getParameter("reservationSeq");
		String repeatNo=request.getParameter("repeatNo");
		
		HashMap<String,Object> reservationDeleteAndInsert = new HashMap<String,Object>();
		
		reservationDeleteAndInsert.put("reservationNo", reservationNumber);
		reservationDeleteAndInsert.put("repeatNo",repeatNo);

		
		
		HashMap<String,Object> reservationDelete = new HashMap<String,Object>();
		
		reservationDelete.put("reservationNo", reservationNumber);
		reservationDelete.put("repeatNo",repeatNo);
		
		
		
		/*
		 * 가예약 및 반복예약 승인 메일
		 * 작성자 : 박성준
		 */
		
		Integer reservationNo = Integer.parseInt(reservationNumber);
		List<Reservation> reservation = reservationDao.getReservationInfo(reservationNo);
		
		String email = reservation.get(ConstantCode.ZERO).getRsvMemEm();
		String subject = "[IBM회의실] " + reservation.get(ConstantCode.ZERO).getRsvMemNm() + "님 예약이 거절됐습니다.";
		String contentTitle = "회의제목 : " + reservation.get(ConstantCode.ZERO).getRsvTitle();
		String contentTime = "회의시간 : " + reservation.get(ConstantCode.ZERO).getRsvStartTime() + " : " + reservation.get(ConstantCode.ZERO).getRsvEndTime();
		String contentURL = ConstantCode.URL;	
		String content = contentTitle + "\n" + contentTime +"\n" +contentURL;
		
		commonService.sendEmail(email, subject, content);
		
		historyDao.reservationDelete(reservationDelete);
		historyDao.reservationDeleteAndInsert(reservationDeleteAndInsert);
		
		return "success";

	}
	
	/**
	 * 작성자  : 최문정
	 * 내용 : 관리자 예약내역 페이지, 입력한 날짜 이전의 날짜를 모두 삭제
	 * @param request
	 * @return
	 */
	public Integer deleteHistoryByDate(HttpServletRequest request) {
		
		String deleteDate = request.getParameter("deleteDate");
		
		historyDao.deleteHistoryByDate(deleteDate);
		return ConstantCode.SUCCESS;
	}
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : History Table과 Reservation Table의 내용을 Controller에 Map 형태로 전달
	 * @param request
	 * @param response
	 * @param dDate
	 */
	public HashMap<String, Object> reservHistoryToExcel(HttpServletRequest request, HttpServletResponse response, String dDate) {
		
		ArrayList<HashMap<String,Object>> historyResult = historyDao.selectAllHistoryByDate(dDate);
		ArrayList<HashMap<String,Object>> reservationResult =  reservationDao.selectAllReservationByDate(dDate);
		
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		//엑셀에 저장할 내용 map에 저장
		map.put("historyTable", historyResult);
		map.put("reservTable", reservationResult);
		map.put("dDate", dDate );
		
		return map;

	}
	
	/* 
	 * 작성자 : 고창환
	 * 가예약 셀렉트 옵션 별 리스트
	 */
	public ArrayList<HashMap<String, Object>> selectResult(HttpServletRequest request) {
		
		ArrayList<HashMap<String, Object>> selectResult = new ArrayList<HashMap<String, Object>>();
		
		if(request.getParameter("searchOption")!=null){
			String selectOpt = request.getParameter("searchOption");
			
			if(selectOpt.equals("allList")) {
				selectResult =  historyDao.fakeReservation();
			}else if(selectOpt.equals("repeatList")) {
				selectResult = historyDao.repeatReservationHead();
			}else if(selectOpt.equals("overtimeList")){
				selectResult = historyDao.overtimeReservation();
			}
		}
		return selectResult;
	}

	/* 
	 * 작성자 : 고창환
	 * 반복예약 상세내역 조회
	 */
	public ArrayList<HashMap<String,Object>> lookInside(HttpServletRequest request) {
		
		ArrayList<HashMap<String, Object>> lookInside = new ArrayList<HashMap<String, Object>>();

		
		Integer repeatNo=Integer.parseInt(request.getParameter("repeatNo"));
		
		
		lookInside = historyDao.repeatReservationInside(repeatNo);
		
		
		return lookInside;
		
	}
	
}
