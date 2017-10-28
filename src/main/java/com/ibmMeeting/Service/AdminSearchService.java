package com.ibmMeeting.Service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.SearchDao;

@Service
public class AdminSearchService {
	
	@Autowired
	SearchDao searchDao;
	
	//Print All Reservation Data
//	public ArrayList<HashMap<String, Object>> allReservList() {
//		return searchDao.allReservList();
//	}
	
	
	
	public ArrayList<HashMap<String, Object>> adminSearchResult(HttpServletRequest request) {
		ArrayList<HashMap<String, Object>> adminSearchResult = new ArrayList<HashMap<String, Object>>();
		
		if(request.getParameter("selectSearchOpt") !=null) {
			String selectOpt = request.getParameter("selectSearchOpt");
			String searchCont = request.getParameter("inputSearchCont");
			
			System.out.println("Service selectOpt : "+ selectOpt + ", searchCont : "+ searchCont);
			
			if(selectOpt.equals("all")) {
				//adminSearchResult =  searchDao.adminSelectByAllList(searchCont);
			}else if(selectOpt.equals("title")) {
				//adminSearchResult = searchDao.adminSelectByTitleList(searchCont);
			}else if(selectOpt.equals("mem_nm")) {
				//adminSearchResult = searchDao.adminSelectByMemNMList(searchCont);
			}else if(selectOpt.equals("mem_pn")) {
				//adminSearchResult = searchDao.adminSelectByMemPNList(searchCont);
			}
			
			System.out.println("Service : "+adminSearchResult);
		}
		return adminSearchResult;
	}
	
	//Admin Repeat Search Result
	public ArrayList<HashMap<String, Object>> adminRepeatSearchResult(HttpServletRequest request) {
		
		ArrayList<HashMap<String, Object>> adminRepeatSearchResult = new ArrayList<HashMap<String, Object>>();
		
		if(request.getParameter("selectSearchOpt") !=null) {
			String selectOpt = request.getParameter("selectSearchOpt");
			String searchCont = request.getParameter("inputSearchCont");
			
			System.out.println("Service selectOpt : "+ selectOpt + ", searchCont : "+ searchCont);
			
			if(selectOpt.equals("all")) {
				//adminRepeatSearchResult =  searchDao.adminRepeatSelectByAllList(searchCont);
			}else if(selectOpt.equals("title")) {
				//adminRepeatSearchResult = searchDao.adminRepeatSelectByTitleList(searchCont);
			}else if(selectOpt.equals("mem_nm")) {
				//adminRepeatSearchResult = searchDao.adminRepeatSelectByMemNMList(searchCont);
			}else if(selectOpt.equals("mem_pn")) {
				//adminRepeatSearchResult = searchDao.adminRepeatSelectByMemPNList(searchCont);
			}
			
			System.out.println("Service : "+adminRepeatSearchResult);
		}
		return adminRepeatSearchResult;
		
	}

}
