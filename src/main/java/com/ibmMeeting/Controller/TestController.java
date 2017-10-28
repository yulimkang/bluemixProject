package com.ibmMeeting.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.BoardingDao;
import com.ibmMeeting.Dao.ReservationDao;
import com.ibmMeeting.Dao.TestDao;
import com.ibmMeeting.Service.CommonService;
import com.ibmMeeting.Service.ReservationService;
import com.ibmMeeting.Service.TestService;


@Controller
@RequestMapping("/Test")
public class TestController {
	
	@Autowired
	TestDao testDao;
	
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	BoardingDao boardingDao;
	
	@Autowired
	ReservationDao reservationDao;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	ReservationService reservationService;

	
	@RequestMapping("/testPage")
	public String testPage(HttpServletRequest request){
		
		System.out.println(reservationDao.similarTitleCheck("안녕하세요"));
	 
	        return "/test";
	}
	
/*	@ResponseBody
	@RequestMapping("/emailFind")
	public Integer emailFind(HttpServletRequest request){
		
		reservationService.emailFind(request);
		return ConstantCode.SUCCESS;
	}
	*/
	
	

}
