package com.ibmMeeting.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	
	@RequestMapping("/Login")
	public String adminLogin(HttpServletRequest request, HttpSession session){
		return "adminLogin";
	}

}
