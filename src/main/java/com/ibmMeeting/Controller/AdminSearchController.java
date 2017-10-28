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
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Service.AdminSearchService;
import com.ibmMeeting.Service.SearchService;


@Controller
@RequestMapping("/AdminSearch")
public class AdminSearchController {
	
	@Autowired
	private AdminSearchService adminSearchService;
	
	@Autowired
	SearchService searchService;
	
//	@RequestMapping("/AdminSearchPage")
//	public String adminSearchPage() {
//		return "admin/admin_search";
//	}
	
	//adminSearchResultList
//	@RequestMapping("/AdminSearchPage")
//	public ModelAndView searchPage(HttpServletRequest request) {
//		
//		String selectOpt = request.getParameter("selectSearchOpt");
//		String searchCont = request.getParameter("inputSearchCont");
//		
//		ModelAndView showAdminSearchResult = new ModelAndView();
//		
//		showAdminSearchResult.addObject("adminSearchResultList", adminSearchService.adminSearchResult(request));
//		showAdminSearchResult.addObject("adminRepeatSearchList", adminSearchService.adminRepeatSearchResult(request));
//		showAdminSearchResult.addObject("selectSearchOptBack", selectOpt);
//		showAdminSearchResult.addObject("inputSearchContBack", searchCont);
//		
//		
//		System.out.println(adminSearchService.adminSearchResult(request));
//		
//		showAdminSearchResult.setViewName("admin/admin_search");
//		
//		
//		return showAdminSearchResult;
//	}
	
	//관리자 검색 일반 페이지
	@RequestMapping("/AdminSearchPage")
	public String AdminSearchPage(HttpServletRequest request ) {
		
		return "/admin/admin_search";

	}
	
	//관리자 일반예약 검색 기능
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
	
	//관리자 반복예약 검색 기능
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
			
			//Service에서 반복예약 출력하는 페이지 따로 만들기
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
	
	
	
	//관리자 반복예약 상세내역 출력
	@ResponseBody
	@RequestMapping(value="/ShowAdminRepeatDetail")
	public ArrayList<HashMap<String,Object>> showRepeatDetail(HttpServletRequest request, ModelMap map ) {
		
		String str = request.getParameter("repeatNo");
		int repeatSeq = Integer.parseInt(str);

		System.out.println("Controller Search Detail Result : " + searchService.showReservDetail(repeatSeq));
		return searchService.showReservDetail(repeatSeq);
		
	}
	
	
	//Search 내용 입력 시 자동완성
	@ResponseBody
	@RequestMapping(value="/AdminSearchAutoComplete")
	public void adminSearchAutocomplete(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		String selectOption = request.getParameter("selectSearchOpt");
		String inputValue = request.getParameter("value");
		System.out.println("Controller Auto Complete : " + selectOption+", Input Value : "+inputValue);
		
		searchService.formAutoComplete(request, response);
		
		//searchService.mempnAutoComplete(request, response);
		
	}
	
	
	
	
}
