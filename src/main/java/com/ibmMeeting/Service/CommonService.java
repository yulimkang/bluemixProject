package com.ibmMeeting.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.MeetingRoomDao;
import com.ibmMeeting.Dao.ReservationDao;

@Service
public class CommonService {
	
	@Autowired
	ReservationDao reservationDao;
	
	@Autowired
	MeetingRoomDao meetingRoomDao;
	
	@Autowired
	public JavaMailSender emailSender;
	
	// 현재 세션이 저장돼 있는지 확인
	public String sessionCheck(HttpSession session){
		
		if(session.getAttribute("id")==null){
			return ConstantCode.FAIL_STRING;
		}
		else{
			return ConstantCode.SUCCESS_STRING;
		}
	}
	
	// 로그아웃
	public void logout(HttpSession session){
		session.invalidate();
	}
	
	// 현재시간 구하는 함수
	public String nowTime(){
		long dateTime = System.currentTimeMillis(); 
		SimpleDateFormat nowDate = new SimpleDateFormat("yyyy/MM/dd");
		String stringDateTime = nowDate.format(new Date(dateTime));
		return stringDateTime;
	}
	
	// 현재시간 구하는 함수
	public String nowTimeDash(){
		long dateTime = System.currentTimeMillis(); 
		SimpleDateFormat nowDate = new SimpleDateFormat("yyyy-MM-dd");
		String stringDateTime = nowDate.format(new Date(dateTime));
		return stringDateTime;
	}
	
	public Integer similarTitleCheck(HttpServletRequest request){
		
		String nameA = "안녕 하세요";
		Integer similarTitleCount = reservationDao.similarTitleCheck(nameA);
		
		return similarTitleCount;
	}
	
	// 이메일 보내는 함수 구현
	public void sendEmail(String email, String subject, String content) throws MessagingException{
		
		  MimeMessage message = emailSender.createMimeMessage();
		  
		  message.setFrom("ibm.gbs.meeting@gmail.com");  
		  message.addRecipient(RecipientType.TO, new InternetAddress(email));
		  message.setSubject(subject);
		  message.setText(content, "utf-8", "html");
		   
		  emailSender.send(message);
	}
	
	public String dayOfTheWeek(String date) throws ParseException {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date rsvDateTypeChange = transFormat.parse(date);
		Calendar rsvDateCal = Calendar.getInstance();
		rsvDateCal.setTime(rsvDateTypeChange);
		
		String dayOfTheWeek = null;
		
		switch(rsvDateCal.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			dayOfTheWeek = "일";
			break;
		case 2:
			dayOfTheWeek = "월";
		case 3:
			dayOfTheWeek = "화";
			break;
		case 4:
			dayOfTheWeek = "수";
			break;
		case 5:
			dayOfTheWeek = "목";
		case 6:
			dayOfTheWeek = "금";
		case 7:
			dayOfTheWeek = "토";
		}
		
		return dayOfTheWeek;
	}
	
	public String timeToString(Time time) throws ParseException {
		
		DateFormat df = new SimpleDateFormat("HH:mm");

		Date today = time;        
		String reportDate = df.format(today);

		
		return reportDate;
	}
	
	public String DateToString(Date date) {
		Date from = new Date();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = transFormat.format(from);
		
		return dateString;

	}
	
	public String confName(int confNo) {
		
		return meetingRoomDao.meetingRoomName(confNo);
	}


}
