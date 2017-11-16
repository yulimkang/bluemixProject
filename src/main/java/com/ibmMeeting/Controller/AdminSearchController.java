package com.ibmMeeting.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibmMeeting.Service.SearchService;


@Controller
@RequestMapping("/AdminSearch")
public class AdminSearchController {
	
	
	@Autowired
	SearchService searchService;
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 페이지에서 예약내역 탭 클릭시 처음 보이는 화면으로 연결
	 * @param request
	 * @return
	 */
	@RequestMapping("/AdminSearchPage")
	public String AdminSearchPage(HttpServletRequest request ) {
		
		return "/admin/admin_search";

	}
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자의 일반예약 검색 기능
	 * @param request
	 * @param page
	 * @param generalSort
	 * @param inputSearchCont
	 * @param selectSearchOpt
	 * @param map
	 * @return
	 */
	@RequestMapping("/AdminGeneralSearchPage")
	public String adminSearchPage(HttpServletRequest request, 
			@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="sort", defaultValue="new") String generalSort, 
			@RequestParam(value="inputSearchCont", defaultValue="") String inputSearchCont, @RequestParam(value="selectSearchOpt", defaultValue="all") String selectSearchOpt,
			ModelMap map ) {
		
		int searchpage = page;
		String sortKind = generalSort;
		String selectOpt = selectSearchOpt;
		String searchCont = inputSearchCont;
		
		HashMap<String, Object> pagebeanMap = searchService.searchResult(request, searchCont, selectOpt , searchpage, sortKind);
		
		
		map.addAttribute("searchResultListA", pagebeanMap.get("searchResult"));
		map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
		map.addAttribute("generalSort", sortKind);
		map.addAttribute("inputSearchCont", searchCont);
		map.addAttribute("selectSearchOpt", selectOpt);
		
		
		map.addAttribute("selectSearchOptBack", selectOpt);
		map.addAttribute("inputSearchContBack", searchCont);
		map.addAttribute("generalSortTypeBack", sortKind);
		
		return "/admin/admin_search_general";
	}
	

	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 검색의 반복예약 검색 기능
	 * @param request
	 * @param page
	 * @param repeatSort
	 * @param inputSearchCont
	 * @param selectSearchOpt
	 * @param map
	 * @return
	 */
	@RequestMapping("/AdminRepeatSearchPage") 
	public String adminRepeatSearchPage(HttpServletRequest request, 
			@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="sort", defaultValue="new") String repeatSort, 
			@RequestParam(value="inputSearchCont", defaultValue="") String inputSearchCont, @RequestParam(value="selectSearchOpt", defaultValue="all") String selectSearchOpt,
			ModelMap map ) {
		
			int searchpage = page;
			String sortKind = repeatSort;
			String selectOpt = selectSearchOpt;
			String searchCont = inputSearchCont;
			
			HashMap<String, Object> pagebeanMap = searchService.repeatSearchResult(request, searchCont, selectOpt , searchpage, sortKind);
			
			
			map.addAttribute("repeatSearchResultListA", pagebeanMap.get("searchResult"));
			map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
			map.addAttribute("generalSort", sortKind);
			map.addAttribute("inputSearchCont", searchCont);
			map.addAttribute("selectSearchOpt", selectOpt);
			
			
			map.addAttribute("selectSearchOptBack", selectOpt);
			map.addAttribute("inputSearchContBack", searchCont);
			map.addAttribute("repeatSortTypeBack", sortKind);
		
		return "/admin/admin_search_repeat";
		
	}
	
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 검색 페이지에서 반복예약내역 검색 시 상세내역 출력
	 * @param request
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ShowAdminRepeatDetail")
	public ArrayList<HashMap<String,Object>> showRepeatDetail(HttpServletRequest request, ModelMap map ) {
		
		String str = request.getParameter("repeatNo");
		int repeatSeq = Integer.parseInt(str);

		return searchService.showReservDetail(repeatSeq);
		
	}
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 검색 기능에서의 자동완성기능
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/AdminSearchAutoComplete")
	public void adminSearchAutocomplete(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		String selectOption = request.getParameter("selectSearchOpt");
		String inputValue = request.getParameter("value");
		
		searchService.formAutoComplete(request, response);
		
	}
	
	
	
	@RequestMapping("/AdminEasySearch")
	public String adminEasySearchPage(HttpServletRequest request, 
			@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="easyInputSearchCont", defaultValue="") String inputSearchCont, 
			@RequestParam(value="easySelectSearchOpt", defaultValue="all") String selectSearchOpt,
			ModelMap map ) {
		
//		int searchpage = page;
//		String selectOpt = selectSearchOpt;
//		String searchCont = inputSearchCont;
//		
//		HashMap<String, Object> pagebeanMap = searchService.searchResult(request, searchCont, selectOpt , searchpage);
//		
//		
//		map.addAttribute("searchResultListA", pagebeanMap.get("searchResult"));
//		map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
//		map.addAttribute("inputSearchCont", searchCont);
//		map.addAttribute("selectSearchOpt", selectOpt);
//		
//		
//		map.addAttribute("selectSearchOptBack", selectOpt);
//		map.addAttribute("inputSearchContBack", searchCont);
//		
//		System.out.println("Admin Controller 일반예약내역 결과 : " + pagebeanMap);
		
		
		return "/admin/admin_search_general";
	}
	
	
	
}
