package com.ibmMeeting.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Service.MeetingRoomManagementService;
import com.ibmMeeting.VO.Conference;
import com.ibmMeeting.VO.Reservation;


@Controller
@RequestMapping("/MeetingRoom")
public class MeetingRoomController {
	
	@Autowired
	MeetingRoomManagementService meetingRoomManagementService;
	
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
	
	@RequestMapping("/GetResources")
	@ResponseBody
	public List<Conference> getResources(){
		
		List<Conference> list = meetingRoomManagementService.getResources();
		
		return list;
	}
	
}


