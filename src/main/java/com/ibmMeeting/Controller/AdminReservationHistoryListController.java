
package com.ibmMeeting.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Dao.SearchDao;
import com.ibmMeeting.Service.HistoryService;
import com.ibmMeeting.Service.ReservationService;
import com.ibmMeeting.Service.SearchService;
import com.ibmMeeting.excel.ExcelInputService;
import com.ibmMeeting.excel.MyExcelView;

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
	
	@Autowired
	ExcelInputService excelInputService;
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 '예약내역' 페이지로 이동
	 * @return
	 */
	@RequestMapping("/ReservHistory")
	public String reservHistroyPage(){
		
		return "/admin/admin_reservationList";
	}
	
	@RequestMapping("/excelDownload")
	public ModelAndView searchPage(HttpServletRequest request,HttpServletResponse response) throws MessagingException, ParseException {
		HashMap<String, Object> model = excelInputService.historyExcelInput(request);
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition","attachment; filename=reservationHisotry.xls");
		return new ModelAndView(new MyExcelView(), model);
	}
	

	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 '예약내역' 페이지에서 입력한 날짜 이전의 기록을 삭제
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/DeleteReservAndHistory")
	public Integer deleteReservHistory(HttpServletRequest request){
		
		int hsitroySuccess = historyService.deleteHistoryByDate(request);
		int reservSuccess =  reservationService.deleteReservationByDate(request);
		
		if(hsitroySuccess == reservSuccess) {
			return 1;
		}else {
			return 0;
		}
		
	}
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 '예약내역' 페이지에서 일반예약 History 검색 결과
	 * @param request
	 * @param page
	 * @param sort
	 * @param selectStartDate
	 * @param selectEndDate
	 * @param map
	 * @return
	 */
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
	

	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 '예약내역' 페이지에서 반복예약 History 검색 결과
	 * @param request
	 * @param page
	 * @param sort
	 * @param selectStartDate
	 * @param selectEndDate
	 * @param map
	 * @return
	 */
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

		
		HashMap<String, Object> pagebeanMap = searchService.searchRepeatHistoryResult(request, searchpage, sortKind, startDate, endDate);
		
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

	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 '예약내역' 페이지에서 반복예약 History 검색 결과 중 상세내역 결과 리턴
	 * @param request
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ShowRepeatDetail")
	public ArrayList<HashMap<String,Object>> showRepeatDetail(HttpServletRequest request, ModelMap map ) {
		
		String str = request.getParameter("repeatNo");
		int repeatSeq = Integer.parseInt(str);
		
		return searchService.showHistDetail(repeatSeq);
		
	}
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 '예약내역' 페이지에서 노쇼(NOSHWO)한 회의목록 출력
	 * @param request
	 * @param page
	 * @param sort
	 * @param selectStartDate
	 * @param selectEndDate
	 * @param map
	 * @return
	 */
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

		HashMap<String, Object> pagebeanMap = searchService.searchNoshowHistoryResult(request, searchpage, sortKind, startDate, endDate);
		
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
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 입력한 날 이전 기록 엑셀로 저장(History Table, Reservation Table)
	 * @param request
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/StoreResevHistToExcel") 
	public ModelAndView storeHistoryToExcel(HttpServletRequest req, HttpServletResponse res) {
		
		String dDate = req.getParameter("deleteDate");		
		HashMap<String, Object> model = historyService.reservHistoryToExcel(req, res, dDate);
		
		return new ModelAndView("excelXlsxView", model);
		
	}
	
	

	
}
