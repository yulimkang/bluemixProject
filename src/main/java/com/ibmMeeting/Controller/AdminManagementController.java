package com.ibmMeeting.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Service.AdminBoardingService;
import com.ibmMeeting.Service.AdminNoShowService;
import com.ibmMeeting.Service.CommonService;
import com.ibmMeeting.Service.MeetingRoomManagementService;
import com.ibmMeeting.Service.ReservationService;
import com.ibmMeeting.Service.SettingService;


@Controller
@RequestMapping("/AdminManagement")
public class AdminManagementController {
	
	@Autowired
	MeetingRoomManagementService adminRoomManagementService;
	
	@Autowired
	SettingService settingService;
	
	@Autowired
	AdminBoardingService adminBoardingService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	AdminNoShowService adminNoShowService;
	
	/**
	 * 작성자 : 박성준
	 * NoShow 클릭
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	
	@ResponseBody
	@RequestMapping("/NoShowSubmit")
	public Integer noShowSubmit(HttpServletRequest request) throws MessagingException, ParseException{
		adminNoShowService.noShowSubmit(request);
		return ConstantCode.SUCCESS;
	}
	
	/**
	 * 작성자 : 박성준
	 * NoShow 관리 페이지 출력
	 */
	
	@RequestMapping("/NoShowManagement")
	public ModelAndView noShowManagement(HttpServletRequest request){
		
		ModelAndView noShowManagementInforamtion = new ModelAndView();
		ArrayList<HashMap<String,Object>> allReservation = reservationService.allReservationList(request);
		noShowManagementInforamtion.addObject("allReservation",allReservation);
		
		noShowManagementInforamtion.addObject("nowDate",commonService.nowTime());
		noShowManagementInforamtion.setViewName("/admin/admin_no_show_manage");
		return noShowManagementInforamtion;
	}
	
	/**
	 * 작성자 : 박성준
	 * NoShow 관리 페이지 출력
	 */
	@RequestMapping("/NoShowAllManage")
	public ModelAndView noShowAllManage(HttpServletRequest request){
		ModelAndView noShowInformation = new ModelAndView();
		ArrayList<HashMap<String,Object>> noShowUserList = adminBoardingService.noShowUserList();
		ArrayList<HashMap<String,Object>> noShowReservationList = adminBoardingService.noShowReservitonList(request);
		noShowInformation.addObject("noShowUserList",noShowUserList);
		noShowInformation.addObject("noShowReservationList",noShowReservationList);
		noShowInformation.setViewName("/admin/admin_no_show_admin_frame");
		return noShowInformation;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/noShowReservationDateChange")
	public ArrayList<HashMap<String,Object>> noShowReservationDateChange(HttpServletRequest request){
		
		ArrayList<HashMap<String,Object>> noShowReservationList = adminBoardingService.noShowReservitonList(request);

		return noShowReservationList;
	}
	
	@ResponseBody
	@RequestMapping("/noShowReservationCancel")
	public Integer noShowReservationCancel(HttpServletRequest request){
		
		adminNoShowService.noShowReservationCancel(request);
		
		return ConstantCode.SUCCESS;
	}
	
	
	/**
	 * 작성자 : 박성준
	 * NoShow 사용자 상세보기
	 */
	@ResponseBody
	@RequestMapping("/NoShowDetailPopUp")
	public ArrayList<HashMap<String,Object>> noShowDetailPopUp(HttpServletRequest request){
		return  adminNoShowService.noShowDetail(request);
	}
	
	
	
	
	/**
	 * 작성자 : 박성준
	 * 설정값에 저장된 NoShow Setting 값 불러오기
	 */
	@ResponseBody
	@RequestMapping("/NoShowValueSetting")
	public Integer noShowValueSetting(HttpServletRequest request){
		adminBoardingService.noShowValueSetting(request);
		return ConstantCode.SUCCESS;
	}
	
	
	/**
	 * 작성자 : 박성준
	 * 설정페이지 출력
	 */
	@RequestMapping("/Setting")
	public ModelAndView setting(HttpServletRequest request, HttpSession session){
		
		ModelAndView setting = new ModelAndView();
		HashMap<String,Object> settingValue = settingService.settingLoad();
		setting.addObject("settingValue",settingValue);
		
		setting.setViewName("/admin/admin_setting");
		
		return setting;
	}
	
	
	/**
	 * 작성자 : 박성준
	 * 설정 값 ajax를 이용한 load
	 */
	@ResponseBody
	@RequestMapping("/SettingLoad")
	public HashMap<String,Object> settingLoad(){
		HashMap<String,Object> settingLoad = settingService.settingLoad();
		return settingLoad;
	}
	
	/**
	 * 작성자 : 박성준
	 * 모든 설정 값 변경 , 각자 마다 다른 option으로 저장
	 */
	@ResponseBody
	@RequestMapping("/SettingSubmit")
	public Integer settingSubmit(HttpServletRequest request){
		settingService.settingSubmit(request);
		return ConstantCode.SUCCESS;
	}
	
	/**
	 * 작성자 : 박성준
	 * 관리자 비밀번호 변경
	 */
	@ResponseBody
	@RequestMapping("/PasswordChange")
	public Integer passwordChange(HttpServletRequest request, HttpSession session){
		settingService.passwordChange(request,session);
		return ConstantCode.SUCCESS;
	}
	
	/**
	 * 작성자 : 박성준
	 * 관리자 이메일 변경
	 */
	@ResponseBody
	@RequestMapping("/EmailChange")
	public Integer emailChange(HttpServletRequest request, HttpSession session){
		settingService.emailChange(request,session);
		return ConstantCode.SUCCESS;
	}
	
}


