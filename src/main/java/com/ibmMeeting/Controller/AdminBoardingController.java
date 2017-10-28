package com.ibmMeeting.Controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Service.AdminBoardingService;
import com.ibmMeeting.Service.DatabaseSettingService;


@Controller
@RequestMapping("/AdminBoarding")
public class AdminBoardingController {
	
	@Autowired
	AdminBoardingService adminboardingService;
	
	@Autowired
	DatabaseSettingService databaseSettingService;
	
/*	@RequestMapping("/")
	public String Boarding(HttpServletRequest request, HttpSession session){
		
		
		return "/admin/admin_onboarding";
	}
	*/
//	@RequestMapping("/Onboarding")
//	public ModelAndView Boarding(HttpServletRequest request, HttpSession session,HttpServletResponse response) throws UnsupportedEncodingException{
//		ModelAndView Boarding = new ModelAndView();
//		Boarding.addObject("BoardingListAll",adminboardingService.BoardingAll());
//		System.out.println(adminboardingService.BoardingAll());
//		Boarding.setViewName("/admin/admin_onboarding");
//		return Boarding;
//	}
	
	/* 
	 * 작성자 : 고창환
	 * 온보딩리스트
	 */
	@RequestMapping("/Onboarding")
	public String Boarding(HttpServletRequest request, HttpSession session,HttpServletResponse response,
			@RequestParam(value="page", defaultValue="1") int page,ModelMap map) throws UnsupportedEncodingException{
		
		
		int boardingPage=page;
		
		HashMap<String, Object> pagebeanMap = adminboardingService.boardingPage(request,boardingPage);
		
		

		map.addAttribute("BoardingListAll", pagebeanMap.get("searchResult"));
		map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
		
		System.out.println("page : "+ page);
		System.out.println("Controller List: "+pagebeanMap.get("searchResult"));

		
		
		
		return "/admin/admin_onboarding";
	}
	/* 
	 * 작성자 : 고창환
	 * 오프보딩리스트
	 */
	@RequestMapping("/Offboarding")
	public ModelAndView offBoarding(HttpServletRequest request, HttpSession session,HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView offBoarding = new ModelAndView();
		offBoarding.addObject("offBoardingListAll",adminboardingService.offBoardingAll());
		offBoarding.setViewName("/admin/admin_offboarding");
		
		return offBoarding;
	}
	/* 
	 * 작성자 : 고창환
	 * 온보딩검색
	 */
	@RequestMapping("/OnSearching")
	public ModelAndView onSearchPage(HttpServletRequest request) {
		ModelAndView showResult = new ModelAndView();
		
		showResult.addObject("BoardingListAll", adminboardingService.searchResult(request));
		
		showResult.setViewName("/admin/admin_onboarding");
		
		return showResult;
	}
	/* 
	 * 작성자 : 고창환
	 * 오프보딩검색
	 */
	@RequestMapping("/OffSearching")
	public ModelAndView offSearchPage(HttpServletRequest request) {
		ModelAndView showResult = new ModelAndView();
		
		showResult.addObject("offBoardingListAll", adminboardingService.offSearchResult(request));
		
		showResult.setViewName("/admin/admin_offboarding");
		
		return showResult;
	}

	
	/* 
	 * 작성자 : 고창환
	 * 온보딩 멤버 수정
	 */
	@ResponseBody
	@RequestMapping("/OnBoardingUpdate")
	public Integer memberUpdate(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		adminboardingService.memberUpdate(request);
		return ConstantCode.SUCCESS;
	}
	
	
	
	/**
	 * 작성자 : 박성준
	 * No-Show 사용자 차단
	 * ajax 이용
	 */
	@ResponseBody
	@RequestMapping("/MemberBan")
	public Integer memberBan(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		adminboardingService.memberBan(request);
		return ConstantCode.SUCCESS;
	}
	
	
	/**
	 * 작성자 : 박성준
	 * 차단된 사용자인지 체크
	 * ajax 이용
	 */
	@ResponseBody
	@RequestMapping("/MemberBanCheck")
	public String memberBanCheck(HttpServletRequest request){
		return adminboardingService.memberBanCheck(request);
	}
	

}
