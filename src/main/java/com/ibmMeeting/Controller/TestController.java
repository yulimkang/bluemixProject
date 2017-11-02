package com.ibmMeeting.Controller;

import java.util.Calendar;
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
	public ModelAndView searchPage(HttpServletRequest request) throws MessagingException {
		
		ModelAndView test = new ModelAndView();

		// 셋팅값에서 이메일 값 가져옴
		HashMap<String, Object> settingValue = settingDao.settingLoad();
		Integer emailTime = (Integer) settingValue.get("SET_EMAIL_TIME");

		// 현재시간에 이메일 셋팅값을 더함
		Calendar nowTimeCal = Calendar.getInstance();
		int hour = nowTimeCal.get(Calendar.HOUR_OF_DAY);
		int min = nowTimeCal.get(Calendar.MINUTE);

		String nowDate = commonService.nowTime();
		String nowTime = Integer.toString(hour) + ":" + Integer.toString(min) + ":00";

		HashMap<String, Object> dateInformation = new HashMap<String, Object>();
		dateInformation.put("nowDate", nowDate);
		dateInformation.put("nowTime", nowTime);

		test.addObject("date", dateInformation);
		test.setViewName("test");
		
		
		String html = "<html><body><h1>Testing</h1><p>This is a <b>test</b> message.</p></body></html> ";
		
		
		commonService.sendEmail("sungjun0204@naver.com","hello",html);
		
		return test;

	}

}
