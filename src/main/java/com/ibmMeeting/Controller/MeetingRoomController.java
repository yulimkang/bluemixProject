package com.ibmMeeting.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Service.MeetingRoomManagementService;

import com.ibmMeeting.Constant.ConstantCode;


@Controller
@RequestMapping("/MeetingRoom")
public class MeetingRoomController {
	
	@Autowired
	MeetingRoomManagementService meetingRoomManagementService;
	
	@Autowired
	ConstantCode constantCode;
	
	@RequestMapping("/RoomManagementList")
	public ModelAndView roomManagement(HttpServletRequest request, HttpSession session){
		
		ModelAndView roomManagement = new ModelAndView();
		roomManagement.addObject("meetingRoomList",meetingRoomManagementService.meetingRoomList());
		roomManagement.setViewName("/admin/admin_room_management");
		
		return roomManagement;
	}
	
	@ResponseBody
	@RequestMapping("/MeetingRoomAdd")
	public Integer meetingRoomAdd(HttpServletRequest request){
		meetingRoomManagementService.meetingRoomAdd(request);
		return ConstantCode.SUCCESS;
	}
	
	@ResponseBody
	@RequestMapping("/MeetingRoomUpdate")
	public Integer meetingRoomUpdate(HttpServletRequest request){
				
		meetingRoomManagementService.meetingRoomUpdate(request);
		return ConstantCode.SUCCESS;
	}
	
	@ResponseBody
	@RequestMapping("/MeetingRoomDelete")
	public Integer meetingRoomDelete(HttpServletRequest request){
		
		meetingRoomManagementService.meetingRoomDelete(request);
		return ConstantCode.SUCCESS;
	}
	
}


