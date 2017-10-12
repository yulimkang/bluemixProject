package com.ibmMeeting.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Service.AdminBoardingService;


@Controller
@RequestMapping("/AdminBoarding")
public class AdminBoardingController {
	
	@Autowired
	AdminBoardingService adminboardingService;
	
	
/*	@RequestMapping("/Onboarding")
	public String Boarding(HttpServletRequest request, HttpSession session){
		
		
		return "/admin/admin_onboarding";
	}
	*/
	@RequestMapping("/Onboarding")
	public ModelAndView Boarding(HttpServletRequest request, HttpSession session){
		
		ModelAndView Boarding = new ModelAndView();
		Boarding.addObject("BoardingListAll",adminboardingService.BoardingAll());
		System.out.println(adminboardingService.BoardingAll());
		Boarding.setViewName("/admin/admin_onboarding");
		
		return Boarding;
	}
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/MemberBan")
	public Integer memberBan(HttpServletRequest request){
		adminboardingService.memberBan(request);
		return ConstantCode.SUCCESS;
	}
	

}
