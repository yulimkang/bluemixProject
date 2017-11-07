package com.ibmMeeting.Controller;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Dao.SchedulerDao;
import com.ibmMeeting.Dao.SearchDao;
import com.ibmMeeting.Dao.SettingDao;
import com.ibmMeeting.Service.CommonService;
import com.ibmMeeting.Service.SearchService;

@Controller
@RequestMapping("/Test")
public class TestController {

	@Autowired
	SearchDao searchDao;

	@Autowired
	SearchService searchService;

	@Autowired
	CommonService commonService;

	@Autowired
	SettingDao settingDao;

	@Autowired
	SchedulerDao schedulerDao;
	
	@RequestMapping("/check")
	public ModelAndView searchPage(HttpServletRequest request) throws MessagingException, ParseException {
		
		ModelAndView test = new ModelAndView();

		
		test.setViewName("test");
		System.out.println("123123213");
		
		
		//셋팅값에서 이메일 값 가져옴
				
		
		return test;

	}

}
