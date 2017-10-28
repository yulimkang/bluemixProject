package com.ibmMeeting.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.BoardingDao;
import com.ibmMeeting.Dao.HistoryDao;
import com.ibmMeeting.Dao.ReservationDao;
import com.ibmMeeting.Dao.SettingDao;
import com.ibmMeeting.VO.Reservation;

@Service
public class AdminNoShowService {
	
	@Autowired
	ReservationDao reservationDao;
	
	@Autowired
	BoardingDao boardingDao;
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	SettingDao settingDao;
	
	@Autowired
	HistoryDao historyDao;
	
	@Autowired
	public JavaMailSender emailSender;

	public Integer noShowSubmit(HttpServletRequest request){
		
		Integer rsvNo = Integer.parseInt(request.getParameter("rsvNo"));
		Reservation rsvInformation = reservationDao.getReservationInfo(rsvNo).get(ConstantCode.ZERO);
		
		HashMap<String,Object> settingValue = settingDao.settingLoad();
		Integer noShowBanDay = (Integer)settingValue.get("SET_NO_SHOW_BAN_DAY");
		
		Calendar getCalendar = Calendar.getInstance();
		getCalendar.add(Calendar.DATE, noShowBanDay);
		DateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String afterBanDayString = transFormat.format(getCalendar.getTime());
		
		HashMap<String,Object> memberInformation = new HashMap<String,Object>();
		memberInformation.put("memberName", rsvInformation.getRsvMemNm());
		memberInformation.put("memberPhone", rsvInformation.getRsvMemPn());
		memberInformation.put("nowDate", afterBanDayString);
		
		boardingDao.noShowCountPlusInManage(memberInformation);
		
		
		String subject = rsvInformation.getRsvMemNm()+ "님 No-Show 카운트가 증가 됐습니다.";
		String contentTitle = "회의제목 : " + rsvInformation.getRsvTitle();
		String contentBody = "회의시작시간 : " + rsvInformation.getRsvStartTime();
		String contentURL = "http://bluemixb.mybluemix.net/";
		
		if(boardingDao.noShowUserCount(memberInformation)==ConstantCode.THREE){
			boardingDao.memberBan(memberInformation);
			
			subject = rsvInformation.getRsvMemNm()+ "님 No-Show 카운트 설정값을 초과하여 차단됩니다.";
			contentTitle = "차단기간 : " + afterBanDayString;
			contentBody = "문의는 해당메일로 답장 부탁드립니다.";
			contentURL = "http://bluemixb.mybluemix.net/";
		}
		
		
		 
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(rsvInformation.getRsvMemEm());
		message.setSubject(subject);
		message.setText(contentTitle+"\n"+ contentBody+"\n"+ contentURL);
		emailSender.send(message);
		
		// history Update
		String hstState = "NOSHOW";
		historyService.insertHistory(rsvInformation, hstState);
		
		// reservation Delete
		reservationDao.deleteReservation(rsvNo);
		
		return ConstantCode.SUCCESS;
	}
	
	
	public ArrayList<HashMap<String,Object>> noShowDetail(HttpServletRequest request){
		
		String userName = request.getParameter("detailMemberName");
		String userPhone = request.getParameter("detailMemberPhone");
		
		Calendar today = Calendar.getInstance();
		String todayYear = Integer.toString(today.get(Calendar.YEAR));
		String todayMonth = Integer.toString(today.get(Calendar.MONTH)+1);
		
		String todayMonthFirstDay = todayYear + "/" + todayMonth+ "/" + 01;
		String todayMonthLastDay = todayYear + "/" + todayMonth+ "/" + 31;
		
		HashMap<String,Object> noShowDetailInformation = new HashMap<String,Object>();
		noShowDetailInformation.put("userName",userName);
		noShowDetailInformation.put("userPhone",userPhone);
		noShowDetailInformation.put("todayMonthFirstDay",todayMonthFirstDay);
		noShowDetailInformation.put("todayMonthLastDay",todayMonthLastDay);
		
		return boardingDao.noShowDetail(noShowDetailInformation);
	}
	
	
	public Integer noShowReservationCancel(HttpServletRequest request){
		
		Integer hstNo = Integer.parseInt(request.getParameter("hstNo"));
		
		HashMap<String,Object> historyAndUserInfo = historyDao.getHistoryAndUserInfo(hstNo);
		HashMap<String,Object> settingLoad = settingDao.settingLoad();
		HashMap<String,Object> updateInfo = new HashMap<String,Object>();
		
		Integer userNoShowLimit = (Integer)settingLoad.get("SET_NO_SHOW_COUNT");
		Integer userNoShowCount = (Integer)historyAndUserInfo.get("COUNT_WARN");
		
		
		if(userNoShowCount<=userNoShowLimit){
			updateInfo.put("MEM_BANDAY", ConstantCode.NOT);
			updateInfo.put("MEM_STATE", ConstantCode.NORMAL);
		}
		else{
			updateInfo.put("MEM_BANDAY", historyAndUserInfo.get("MEM_BANDAY"));
			updateInfo.put("MEM_STATE", historyAndUserInfo.get("MEM_STATE"));
		}
		
		updateInfo.put("COUNT_WARN", userNoShowCount - ConstantCode.ONE);
		updateInfo.put("MEM_PN", historyAndUserInfo.get("MEM_PN"));
		
		// history update
		
		historyDao.historyNoShowCancel(hstNo);
		boardingDao.noShowCountMinusInReservation(updateInfo);
		
		return ConstantCode.SUCCESS;
	}
	
}
