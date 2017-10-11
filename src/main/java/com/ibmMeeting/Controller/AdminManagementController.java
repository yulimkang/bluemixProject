package com.ibmMeeting.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Service.MeetingRoomManagementService;
import com.ibmMeeting.Service.SettingService;


@Controller
@RequestMapping("/AdminManagement")
public class AdminManagementController {
	
	@Autowired
	MeetingRoomManagementService adminRoomManagementService;
	
	@Autowired
	SettingService settingService;
	
	@RequestMapping("/DashBoard")
	public String dashBoard(HttpServletRequest request, HttpSession session){
		return "/admin/admin_reservationList";
	}
	
	@RequestMapping("/Setting")
	public ModelAndView setting(HttpServletRequest request, HttpSession session){
		
		ModelAndView setting = new ModelAndView();
		HashMap<String,Object> settingValue = settingService.settingLoad();
		setting.addObject("settingValue",settingValue);
		setting.setViewName("/admin/admin_setting");
		
		return setting;
	}
	
	@ResponseBody
	@RequestMapping("SettingSubmit")
	public Integer settingSubmit(HttpServletRequest request){
		
		settingService.settingSubmit(request);
		return ConstantCode.SUCCESS;
	}
	
	
}


