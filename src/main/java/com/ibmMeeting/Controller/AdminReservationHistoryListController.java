
package com.ibmMeeting.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.SearchDao;
import com.ibmMeeting.Service.HistoryService;
import com.ibmMeeting.Service.ReservationService;
import com.ibmMeeting.Service.SearchService;

@Controller
@RequestMapping("/AdminReservCheckAndDelete")
public class AdminReservationHistoryListController {
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private SearchDao searchDao;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/ReservHistory")
	public String reservHistroyPage(){
		
		return "/admin/admin_reservationList";
	}
	
	//이전 예약기록 및 History 삭제
	@ResponseBody
	@RequestMapping("/DeleteReservAndHistory")
	public Integer deleteReservHistory(HttpServletRequest request){
		
		String dDate = request.getParameter("deleteDate");
		System.out.println("Delete Date : "+dDate);
		
		int hsitroySuccess = historyService.deleteHistoryByDate(request);
		int reservSuccess =  reservationService.deleteReservationByDate(request);
		//reservationService.deleteReservation(request);
		
		if(hsitroySuccess == reservSuccess) {
			return 1;
		}else {
			return 0;
		}
		
	}
	
	//예약내역 검색
//	@RequestMapping("/ReservHistory")
//	public ModelAndView reservHistroyPage(HttpServletRequest request){
//		
//		String startDate = request.getParameter("selectStartDate");
//		String endDate = request.getParameter("selectEndDate");
//		
//		ModelAndView showHistory = new ModelAndView();
//		
//		showHistory.addObject("searchHistoryResultList", historyService.searchHistoryResult(request));
//		showHistory.addObject("startDateBack", startDate );
//		showHistory.addObject("endDateBack", endDate );
//		//System.out.println(searchService.searchResult(request));
//		
//		System.out.println(historyService.searchHistoryResult(request));
//		
//		showHistory.setViewName("/admin/admin_reservationList");
//		
//		return showHistory;
//	}
//	
	
	//관리자 예약내역 일반예약 HISTORY 검색
	@RequestMapping("/SearchGeneralHistory")
	public String searchGeneralHistory(HttpServletRequest request, 
			@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="sort", defaultValue="new") String sort, 
			@RequestParam(value="selectStartDate") String selectStartDate,
			@RequestParam(value="selectEndDate") String selectEndDate,
			ModelMap map ) {
		
		int searchpage = page;
		String sortKind = sort;
		String startDate = selectStartDate;
		String endDate = selectEndDate;
		
		//String startDate = request.getParameter("selectStartDate");
		//String endDate = request.getParameter("selectEndDate");
		
		
		System.out.println("Controller : page : " + searchpage + ", sort : " + sortKind +", startDate : "+ startDate + ", endDate : " + endDate);
		
		HashMap<String, Object> pagebeanMap = searchService.searchGeneralHistoryResult(request, searchpage, sortKind, startDate, endDate);
		
		//변경하기
		map.addAttribute("searchGeneralHistoryResultList", pagebeanMap.get("repeatHistorySearchResult"));
		map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
		map.addAttribute("sort", sortKind);
		map.addAttribute("selectStartDate", startDate);
		map.addAttribute("selectEndDate", endDate);

		map.addAttribute("sortTypeBack", sortKind);
		
		map.addAttribute("startDateBack", startDate);
		map.addAttribute("endDateBack", endDate);
		
		
		
		return "/admin/admin_reservationList_general";
	}
	
	//관리자 예약내역 반복예약 HISTORY 검색
	@RequestMapping("/SearchRepeatHistory")
	public String searchRepeatHistory (HttpServletRequest request, 
			@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="sort", defaultValue="new") String sort, 
			@RequestParam(value="selectStartDate") String selectStartDate,
			@RequestParam(value="selectEndDate") String selectEndDate,
			ModelMap map ) {
		
		
		int searchpage = page;
		String sortKind = sort;
		String startDate = selectStartDate;
		String endDate = selectEndDate;
		
		//String startDate = request.getParameter("selectStartDate");
		//String endDate = request.getParameter("selectEndDate");
		
		
		System.out.println("Controller : page : " + searchpage + ", sort : " + sortKind +", startDate : "+ startDate + ", endDate : " + endDate);
		
		HashMap<String, Object> pagebeanMap = searchService.searchRepeatHistoryResult(request, searchpage, sortKind, startDate, endDate);
		
		//변경하기
		map.addAttribute("searchRepeatHistoryResultList", pagebeanMap.get("repeatHistorySearchResult"));
		map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
		map.addAttribute("sort", sortKind);
		map.addAttribute("selectStartDate", startDate);
		map.addAttribute("selectEndDate", endDate);

		map.addAttribute("sortTypeBack", sortKind);
		
		map.addAttribute("startDateBack", startDate);
		map.addAttribute("endDateBack", endDate);
		
		
		
		return "/admin/admin_reservationList_repeat";
		
	}
	
	//반복예약내역 상세보기
	@ResponseBody
	@RequestMapping(value="/ShowRepeatDetail")
	public ArrayList<HashMap<String,Object>> showRepeatDetail(HttpServletRequest request, ModelMap map ) {
		
		String str = request.getParameter("repeatNo");
		int repeatSeq = Integer.parseInt(str);
		
		System.out.println("repeatNo : "+ repeatSeq);

		System.out.println("Controller Search Detail Result : " + searchService.showHistDetail(repeatSeq));
		return searchService.showHistDetail(repeatSeq);
		
	}
	
	
	//관리자 예약내역 NOSHOW HISTORY 검색
	@RequestMapping("/SearchNoshowHistory")
	public String searchNoshowHistory (HttpServletRequest request, 
			@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="sort", defaultValue="new") String sort, 
			@RequestParam(value="selectStartDate") String selectStartDate,
			@RequestParam(value="selectEndDate") String selectEndDate,
			ModelMap map ) {
		
		
		int searchpage = page;
		String sortKind = sort;
		String startDate = selectStartDate;
		String endDate = selectEndDate;
		
		//String startDate = request.getParameter("selectStartDate");
		//String endDate = request.getParameter("selectEndDate");
		
		
		System.out.println("Controller : page : " + searchpage + ", sort : " + sortKind +", startDate : "+ startDate + ", endDate : " + endDate);
		
		HashMap<String, Object> pagebeanMap = searchService.searchNoshowHistoryResult(request, searchpage, sortKind, startDate, endDate);
		
		//변경하기
		map.addAttribute("noshowHistorySearchResult", pagebeanMap.get("noshowHistorySearchResult"));
		map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
		map.addAttribute("sort", sortKind);
		map.addAttribute("selectStartDate", startDate);
		map.addAttribute("selectEndDate", endDate);

		map.addAttribute("sortTypeBack", sortKind);
		
		map.addAttribute("startDateBack", startDate);
		map.addAttribute("endDateBack", endDate);
		
		
		
		return "/admin/admin_reservationList_noshow";
		
	}
	
	

	
}
