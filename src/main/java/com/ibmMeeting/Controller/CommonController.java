package com.ibmMeeting.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Service.CommonService;


@Controller
@RequestMapping("/Common")
public class CommonController {
	
	@Autowired
	CommonService commonService;
	
	@ResponseBody
	@RequestMapping("/SessionCheck")
	public String sessionCheck(HttpSession session){
		return commonService.sessionCheck(session);
	}
	
	@RequestMapping("/Logout")
	public String logout(HttpSession session){
		commonService.logout(session);
		return "index/index";
	}
}


