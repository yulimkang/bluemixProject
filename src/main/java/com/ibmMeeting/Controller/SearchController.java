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
		
	}
	
	
	///////////////////////////10월 31일 이후 수정/////////////////////////
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 검색어 입력 시 자동완성하는 기능(간편검색용)
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/EasySearchAutoComplete")
	public void easySearchAutocomplete(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		
		searchService.easyFormAutoComplete(request, response);		
	}	
	

	
	/**
	 * 작성자 :  최문정
	 * 내용 : 사용자 검색기능
	 * @param request
	 * @param page
	 * @param generalSort
	 * @param inputSearchCont
	 * @param selectSearchOpt
	 * @param map
	 * @return
	 */
	@RequestMapping("/GeneralUserSearchPage")
	public String generalUserSearchPage(HttpServletRequest request,
			@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="sort", defaultValue="old") String generalSort, 
			@RequestParam(value="inputSearchCont", defaultValue="") String inputSearchCont, 
			@RequestParam(value="selectSearchOpt", defaultValue="all") String selectSearchOpt,
			@RequestParam(value="sDate", defaultValue="") String sDate,
			@RequestParam(value="eDate", defaultValue="") String eDate,
			ModelMap map ) {
		
			int searchpage = page;
			String sortKind = generalSort;
			String selectOpt = selectSearchOpt;
			String searchCont = inputSearchCont;
			
			HashMap<String, Object> pagebeanMap = searchService.generalUserSearchResult(request, searchCont, selectOpt , searchpage, sortKind);
			
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
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 헤더에서의 간편검색 출력
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/EasySearch")
	public ArrayList<HashMap<String,Object>> generalEasySearchPage(HttpServletRequest request) {
		
			String selectOpt = request.getParameter("easySelectSearchOpt");
			String searchCont = request.getParameter("easyInputSearchCont");
			
		
		return searchService.easySearchResult(request, searchCont, selectOpt);
		
	}
		
}
