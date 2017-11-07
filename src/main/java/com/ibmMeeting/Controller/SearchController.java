package com.ibmMeeting.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.SearchDao;
import com.ibmMeeting.Service.SearchService;


@Controller
@RequestMapping("/Search")
public class SearchController {
	
	@Autowired
	SearchDao searchDao;
	
	@Autowired
	SearchService searchService;
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 사용자 검색페이지에서의 기본 페이지로 이동
	 * @param request
	 * @return
	 */
	@RequestMapping("/SearchPage")
	public String searchPage(HttpServletRequest request ) {
		
		return "/search/search";

	}
	

	/**
	 * 작성자 : 최문정
	 * 내용 : 사용자 검색에서의 일반예약 출력
	 * @param request
	 * @param page
	 * @param generalSort
	 * @param inputSearchCont
	 * @param selectSearchOpt
	 * @param map
	 * @return
	 */
//	@RequestMapping("/GeneralSearchPage")
//	public String searchPage(HttpServletRequest request, 
//			@RequestParam(value="page", defaultValue="1") int page, 
//			@RequestParam(value="sort", defaultValue="new") String generalSort, 
//			@RequestParam(value="inputSearchCont", defaultValue="") String inputSearchCont, @RequestParam(value="selectSearchOpt", defaultValue="all") String selectSearchOpt,
//			ModelMap map ) {
//		
//		int searchpage = page;
//		String sortKind = generalSort;
//		String selectOpt = selectSearchOpt;
//		String searchCont = inputSearchCont;
//		
//		HashMap<String, Object> pagebeanMap = searchService.searchResult(request, searchCont, selectOpt , searchpage, sortKind);
//		
//		
//		map.addAttribute("searchResultListA", pagebeanMap.get("searchResult"));
//		map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
//		map.addAttribute("generalSort", sortKind);
//		map.addAttribute("inputSearchCont", searchCont);
//		map.addAttribute("selectSearchOpt", selectOpt);
//		
//		
//		map.addAttribute("selectSearchOptBack", selectOpt);
//		map.addAttribute("inputSearchContBack", searchCont);
//		map.addAttribute("generalSortTypeBack", sortKind);
//		
//		
//		return "search/general_search";
//	}
//	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 사용자 검색페이지에서의 반복예약  출려
	 * @param request
	 * @param page
	 * @param repeatSort
	 * @param inputSearchCont
	 * @param selectSearchOpt
	 * @param map
	 * @return
	 */
	//사용자 검색 반복예약 페이지 출력
//	@RequestMapping("/RepeatSearchPage") 
//	public String repeatSearchPage(HttpServletRequest request, 
//			@RequestParam(value="page", defaultValue="1") int page, 
//			@RequestParam(value="sort", defaultValue="new") String repeatSort, 
//			@RequestParam(value="inputSearchCont", defaultValue="") String inputSearchCont, @RequestParam(value="selectSearchOpt", defaultValue="all") String selectSearchOpt,
//			ModelMap map ) {
//		
//			int searchpage = page;
//			String sortKind = repeatSort;
//			String selectOpt = selectSearchOpt;
//			String searchCont = inputSearchCont;
//			
//			//Service에서 반복예약 출력하는 페이지 따로 만들기
//			HashMap<String, Object> pagebeanMap = searchService.repeatSearchResult(request, searchCont, selectOpt , searchpage, sortKind);
//			
//			
//			map.addAttribute("repeatSearchResultListA", pagebeanMap.get("searchResult"));
//			map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
//			map.addAttribute("generalSort", sortKind);
//			map.addAttribute("inputSearchCont", searchCont);
//			map.addAttribute("selectSearchOpt", selectOpt);
//			
//			
//			map.addAttribute("selectSearchOptBack", selectOpt);
//			map.addAttribute("inputSearchContBack", searchCont);
//			map.addAttribute("repeatSortTypeBack", sortKind);
//		
//		return "search/repeat_search";
//		
//	}
	
	/**
	 * 작성자  : 최문정
	 * 내용 :사용자 검색 페이지에서 반복예약내역 상세보기 출력
	 * @param request
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ShowRepeatDetail")
	public ArrayList<HashMap<String,Object>> showRepeatDetail(HttpServletRequest request, ModelMap map ) {
		
		String str = request.getParameter("repeatNo");
		int repeatSeq = Integer.parseInt(str);
		
		return searchService.showReservDetail(repeatSeq);
		
	}
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 검색어 입력 시 자동완성하는 기능
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/SearchAutoComplete")
	public void searchAutocomplete(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		String selectOption = request.getParameter("selectSearchOpt");
		String inputValue = request.getParameter("value");
		
		searchService.formAutoComplete(request, response);
		
		System.out.println("Controller AutoComplete : " + inputValue);
		
	}
	
	
	
	
	///////////////////////////10월 31일 이후 수정/////////////////////////
	
	//사용자 검색 페이지
	@RequestMapping("/GeneralUserSearchPage")
	public String generalUserSearchPage(HttpServletRequest request, 
			@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="sort", defaultValue="old") String generalSort, 
			@RequestParam(value="inputSearchCont", defaultValue="") String inputSearchCont, @RequestParam(value="selectSearchOpt", defaultValue="all") String selectSearchOpt,
			ModelMap map ) {
		
			int searchpage = page;
			String sortKind = generalSort;
			String selectOpt = selectSearchOpt;
			String searchCont = inputSearchCont;
			
			
			System.out.println("Controller Sort Check : " + sortKind);
			
			HashMap<String, Object> pagebeanMap = searchService.generalUserSearchResult(request, searchCont, selectOpt , searchpage, sortKind);
			
			System.out.println("Controller Check LIst : " + pagebeanMap);
			
			map.addAttribute("searchResultListA", pagebeanMap.get("searchResult"));
			map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
			map.addAttribute("sort", sortKind);
			map.addAttribute("inputSearchCont", searchCont);
			map.addAttribute("selectSearchOpt", selectOpt);
			
			
			map.addAttribute("selectSearchOptBack", selectOpt);
			map.addAttribute("inputSearchContBack", searchCont);
			map.addAttribute("sortTypeBack", sortKind);
		
		return "/search/general_search";
		
	}
	
	
	//사용자 간편검색 페이지
//		@RequestMapping("/EasySearch")
//		public String generalEasySearchPage(HttpServletRequest request, 
//				@RequestParam(value="page", defaultValue="1") int page, 
//				@RequestParam(value="easyInputSearchCont", defaultValue="") String easyInputSearchCont, 
//				@RequestParam(value="easySelectSearchOpt", defaultValue="all") String easySelectSearchOpt,
//				ModelMap map ) {
//			
//				int searchpage = page;
//				String selectOpt = easySelectSearchOpt;
//				String searchCont = easyInputSearchCont;
//				
//				HashMap<String, Object> pagebeanMap = searchService.easySearchResult(request, searchCont, selectOpt , searchpage);
//				
//				System.out.println("Controller Check LIst Easy Search : " + pagebeanMap);
//				
//				map.addAttribute("searchResultListA", pagebeanMap.get("searchResult"));
//				map.addAttribute("pageBean", pagebeanMap.get("pageBean"));
//				map.addAttribute("easyInputSearchCont", searchCont);
//				map.addAttribute("easySelectSearchOpt", selectOpt);
//				
//				
//				map.addAttribute("easySelectSearchOptBack", selectOpt);
//				map.addAttribute("easyInputSearchContBack", searchCont);
//			
//			return "/headerAndFooter/header";
//			
//		}
	
		@ResponseBody
		@RequestMapping(value="/EasySearch")
		public ArrayList<HashMap<String,Object>> generalEasySearchPage(HttpServletRequest request) {
			
				String selectOpt = request.getParameter("easySelectSearchOpt");
				String searchCont = request.getParameter("easyInputSearchCont");
				
//				HashMap<String, Object> pagebeanMap = searchService.easySearchResult(request, searchCont, selectOpt);
//				
//				System.out.println("Controller Check LIst Easy Search : " + pagebeanMap);
//				
//				map.addAttribute("searchResultListA", pagebeanMap.get("searchResult"));
//				map.addAttribute("easyInputSearchCont", searchCont);
//				map.addAttribute("easySelectSearchOpt", selectOpt);
//				
//				
//				map.addAttribute("easySelectSearchOptBack", selectOpt);
//				map.addAttribute("easyInputSearchContBack", searchCont);
			
			return searchService.easySearchResult(request, searchCont, selectOpt);
			
		}
		
//		@RequestMapping("/Test")
//		public String testLink() {
//			return "/search/easy_search";
//		}
//	


}
