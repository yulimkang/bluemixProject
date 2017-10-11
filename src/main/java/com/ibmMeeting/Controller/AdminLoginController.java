package com.ibmMeeting.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Dao.TestDao;
import com.ibmMeeting.Service.AdminLoginService;
import com.ibmMeeting.Service.MeetingRoomManagementService;


@Controller
@RequestMapping("/AdminLogin")
public class AdminLoginController {
	
	@Autowired
	TestDao testDao;
	
	@Autowired
	AdminLoginService adminLoginService;
	
	
	@RequestMapping("/LoginPage")
	public String adminLogin(HttpServletRequest request, HttpSession session){
		return "/admin/admin_login";
	}
	
	@ResponseBody
	@RequestMapping("/LoginCheck")
	public Integer loginCheck(HttpServletRequest request, HttpSession session){
		
		return adminLoginService.loginCheck(request, session);
	}
	
	

}
