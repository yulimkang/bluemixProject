package com.ibmMeeting.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Service.MeetingRoomManagementService;


@Controller
@RequestMapping("/AdminManagement")
public class AdminManagementController {
	
	@Autowired
	MeetingRoomManagementService adminRoomManagementService;
	
	@RequestMapping("/DashBoard")
	public String dashBoard(HttpServletRequest request, HttpSession session){
		return "/admin/admin_reservationList";
	}
	
	@RequestMapping("/Setting")
	public ModelAndView setting(HttpServletRequest request, HttpSession session){
		
		ModelAndView setting = new ModelAndView();
		HashMap<String,Object> settingValue = new HashMap<String,Object>();
		
		settingValue.put("ReservationMaxMonth", Integer.toString(ConstantCode.ReservationMaxMonth));
		settingValue.put("ReservationEmailTime", Integer.toString(ConstantCode.ReservationMaxMonth));
		settingValue.put("ReservationNoShowCount", Integer.toString(ConstantCode.ReservationMaxMonth));
		settingValue.put("ReservationNoShowBanDay", Integer.toString(ConstantCode.ReservationMaxMonth));
		settingValue.put("ReservationMaxMonopoly", Integer.toString(ConstantCode.ReservationMaxMonth));
		
		
		Integer ReservationMaxMonth = ConstantCode.ReservationMaxMonth;
		Integer ReservationEmailTime = ConstantCode.ReservationEmailTime;
		Integer ReservationNoShowCount = ConstantCode.ReservationNoShowCount;
		Integer ReservationNoShowBan = ConstantCode.ReservationNoShowBanDay;
		Integer ReservationMaxMonopoly = ConstantCode.ReservationMaxMonopoly;
		
		
		
		
		setting.setViewName("/admin/admin_setting");
		
		return setting;
	}
	
}


