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
	
	//사용자 검색 기본 페이지
	@RequestMapping("/SearchPage")
	public String searchPage(HttpServletRequest request ) {
		
		return "/search/search";

	}
	
	//search.jsp 파일로 이동, Search 결과 출력
	@RequestMapping("/GeneralSearchPage")
	public String searchPage(HttpServletRequest request, 
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
		
		
		return "search/general_search";
	}
	
	//사용자 검색 반복예약 페이지 출력
	@RequestMapping("/RepeatSearchPage") 
	public String repeatSearchPage(HttpServletRequest request, 
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
		
		return "search/repeat_search";
		
	}
	
	//반복예약 상세 내역 출력
	@ResponseBody
	@RequestMapping(value="/ShowRepeatDetail")
	public ArrayList<HashMap<String,Object>> showRepeatDetail(HttpServletRequest request, ModelMap map ) {
		
		String str = request.getParameter("repeatNo");
		int repeatSeq = Integer.parseInt(str);
		
		System.out.println("repeatNo : "+ repeatSeq);

		System.out.println("Controller Search Detail Result : " + searchService.showReservDetail(repeatSeq));
		return searchService.showReservDetail(repeatSeq);
		
	}
	
	
	//Search 내용 입력 시 자동완성
	@ResponseBody
	@RequestMapping(value="/SearchAutoComplete")
	public void searchAutocomplete(HttpServletRequest request, HttpServletResponse response) {
		
		response.setCharacterEncoding("UTF-8");
		String selectOption = request.getParameter("selectSearchOpt");
		String inputValue = request.getParameter("value");
		System.out.println("Controller Auto Complete : " + selectOption+", Input Value : "+inputValue);
		
		searchService.formAutoComplete(request, response);
		
	}

}
