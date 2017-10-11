package com.ibmMeeting.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String IndexPage() {
		
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    try {
		        Date date = df.parse("2013-02-05");
		         
		        // 날짜 더하기
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        cal.add(Calendar.DATE, 30);
		         
		        System.err.println(df.format(cal.getTime()));
		         
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }

		
		return "index/index";
	}
}
