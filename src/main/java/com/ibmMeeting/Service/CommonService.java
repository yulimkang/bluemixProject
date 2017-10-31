package com.ibmMeeting.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.ReservationDao;

@Service
public class CommonService {
	
	@Autowired
	ReservationDao reservationDao;
	
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
	public void sendEmail(String email, String subject, String content){
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(email); // 받을 이메일
		message.setSubject(subject); // 제목 
		message.setText(content); // 내용
		emailSender.send(message); // 전송
	}

}
