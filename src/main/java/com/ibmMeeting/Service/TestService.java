package com.ibmMeeting.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.ReservationDao;
import com.ibmMeeting.VO.Reservation;

@Service
public class TestService {
	
	@Autowired
	ReservationDao reservationDao;
	
	@Autowired
	CommonService commonService;
	
	public void emailFind(HttpServletRequest request){
		
		Integer rsvNo = Integer.parseInt(request.getParameter("rsvNo"));
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
	
}
