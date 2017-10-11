package com.ibmMeeting.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Dao.SearchDao;
import com.ibmMeeting.Service.SearchService;


@Controller
@RequestMapping("/Search")
public class SearchController {
	
	@Autowired
	SearchDao searchDao;
	
	@Autowired
	SearchService searchService;
	
/*	@RequestMapping("/SearchPage")
	public String searchPage(HttpServletRequest request) {
		return "/search/search";
	}
*/
	
	@RequestMapping("/SearchPage")
	public ModelAndView showAllReserv(HttpServletRequest request) {
		
		ModelAndView showAllReserv = new ModelAndView();
		System.out.println(searchService.allReservList());
		showAllReserv.addObject("allReservList", searchService.allReservList());
		showAllReserv.setViewName("/search/search");
		
		return showAllReserv;
	}
	
}
