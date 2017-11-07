package com.ibmMeeting.Controller;

import java.text.ParseException;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Dao.SchedulerDao;
import com.ibmMeeting.Dao.SearchDao;
import com.ibmMeeting.Dao.SettingDao;
import com.ibmMeeting.Service.CommonService;
import com.ibmMeeting.Service.SearchService;
import com.ibmMeeting.excel.ExcelInputService;
import com.ibmMeeting.excel.MyExcelView;

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
	
	@Autowired
	ExcelInputService excelInputService;
	
	@RequestMapping("/test")
	public String testPage(){
		return "test";
	}

	@RequestMapping("/check")
	public ModelAndView searchPage(HttpServletRequest request,HttpServletResponse response) throws MessagingException, ParseException {
		
		HashMap<String, Object> model = excelInputService.excelInput();
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition","attachment; filename=myfile.xls");
		return new ModelAndView(new MyExcelView(), model);
	}

}
