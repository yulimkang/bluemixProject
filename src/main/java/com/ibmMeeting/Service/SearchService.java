package com.ibmMeeting.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.SearchDao;
import com.ibmMeeting.paging.util.PagingBean;

@Service
public class SearchService {
	
	@Autowired
	SearchDao searchDao;
	
	//Print All Reservation Data
//	public ArrayList<HashMap<String, Object>> allReservList() {
//		return searchDao.allReservList();
//	}
	

	//일반예약 출력		
	public HashMap<String, Object> searchResult(HttpServletRequest request, String searchCont, String selectOpt, int page, String generalSort) {
		
		ArrayList<HashMap<String, Object>> searchResult = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String,Object> searchInfo = new HashMap<String,Object>();
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		//String selectOpt = request.getParameter("selectSearchOpt");
		//String searchCont = request.getParameter("inputSearchCont");
		
		
		if(request.getParameter("selectSearchOpt")!=null){
			
			
			System.out.println("Service selectOpt : "+ selectOpt + ", searchCont : "+ searchCont );
			
			if(selectOpt.equals("all") && generalSort.equals("new")) {
				
				//'전체'옵션 검색, 최신순 정렬
				int totalCount = searchDao.rownumSelectByAll(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());

				searchResult =  searchDao.generalSelectByAllOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			}else if(selectOpt.equals("all") && generalSort.equals("old")){
				
				//'전체'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao.rownumSelectByAll(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());
				
				searchResult =  searchDao.generalSelectByAllOrderByOldList(searchInfo);
				
				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);
				
			}else if(selectOpt.equals("title") && generalSort.equals("new")) {
				
				//'회의제목' 옵션, 최신순 정렬
				int totalCount = searchDao.rownumSelectByTitle(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());
				
				searchResult =  searchDao.generalSelectByTitleOrderByNewList(searchInfo);
				
				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);
				
			}else if(selectOpt.equals("title") && generalSort.equals("old")){
				
				//'회의제목' 옵션, 오래된 순 정렬 정렬
				int totalCount = searchDao.rownumSelectByTitle(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());
				
				searchResult =  searchDao.generalSelectByTitleOrderByOldList(searchInfo);
				
				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);
				
			}else if(selectOpt.equals("mem_nm") && generalSort.equals("new")) {
				
				//'예약자 이름' 옵션, 최신 순 정렬
				int totalCount = searchDao.rownumSelectByMemNM(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());
				
				searchResult =  searchDao.generalSelectByMemNMOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);
				
			}else if(selectOpt.equals("mem_nm") && generalSort.equals("old")) {
				
				//'예약자 이름' 옵션, 오래된 순 정렬
				int totalCount = searchDao.rownumSelectByMemNM(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());
				
				searchResult =  searchDao.generalSelectByMemNMOrderByOldList(searchInfo);
				
				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			}else if(selectOpt.equals("mem_pn") && generalSort.equals("new")) {
				
				//예약자 번호, 최신순 정렬
				int totalCount = searchDao.rownumSelectByMemPN(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);		
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());
				
				searchResult =  searchDao.genelralSelectByMemPNOrderByNewList(searchInfo);
				
				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);

			}else if(selectOpt.equals("mem_pn") && generalSort.equals("old")) {
				
				//예약자 번호, 최신순 정렬
				int totalCount = searchDao.rownumSelectByMemPN(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());
				
				searchResult =  searchDao.genelralSelectByMemPNOrderByOldList(searchInfo);
				
				map.put("pageBean", pageBean);
				map.put("searchResult", searchResult);
				
			}
			
			System.out.println("a"+searchResult);
		}
		
		System.out.println("Service generalPage : " +page+ ", generalSort : " +generalSort);
		
		return map;
		//return searchResult;
	}
	
	
	
	//반복예약 출력
	public HashMap<String, Object> repeatSearchResult(HttpServletRequest request, String searchCont, String selectOpt, int page, String generalSort) {

		ArrayList<HashMap<String, Object>> repeatSearchResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> searchInfo =  new HashMap<String, Object>();
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		
		if(request.getParameter("selectSearchOpt")!=null){
		
				
			System.out.println("Service selectOpt : "+ selectOpt + ", searchCont : "+ searchCont );
			
			if(selectOpt.equals("all") && generalSort.equals("new")) {
				
				//반복예약 '전체'옵션 검색, 최신순 정렬
				int totalCount = searchDao.rownumRepeatSelectByAll(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());

				repeatSearchResult =  searchDao.repeatSelectByAllOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);

			}else if(selectOpt.equals("all") && generalSort.equals("old")){
				
				//반복예약 '전체'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao.rownumRepeatSelectByAll(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());
				
				repeatSearchResult =  searchDao.repeatSelectByAllOrderByOldList(searchInfo);
				
				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);
				
			}else if(selectOpt.equals("title") && generalSort.equals("new")){
				
				//반복예약 '회의제목'옵션 검색, 최신순 정렬
				int totalCount = searchDao.rownumRepeatSelectByTitle(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());

				repeatSearchResult =  searchDao.repeatSelectByTitleOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);
				
			}else if(selectOpt.equals("title") && generalSort.equals("old")){
				
				//반복예약 '회의재목'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao.rownumRepeatSelectByTitle(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());

				repeatSearchResult =  searchDao.repeatSelectByTitleOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);
				
			}else if(selectOpt.equals("mem_nm") && generalSort.equals("new")){
				
				//반복예약 '예약자'옵션 검색, 최신순 정렬
				int totalCount = searchDao.rownumRepeatSelectByMemNM(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());

				repeatSearchResult =  searchDao.repeatSelectByMemNMOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);
				
			}else if(selectOpt.equals("mem_nm") && generalSort.equals("old")){
				
				//반복예약 '예약자'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao.rownumRepeatSelectByMemNM(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());

				repeatSearchResult =  searchDao.repeatSelectByMemNMOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);
				
			}else if(selectOpt.equals("mem_pn") && generalSort.equals("new")){
				
				//반복예약 '예약자 핸드폰 번호'옵션 검색, 최신순 정렬
				int totalCount = searchDao.rownumRepeatSelectByMemPN(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());

				repeatSearchResult =  searchDao.repeatSelectByMemPNOrderByNewList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);
				
			}else if(selectOpt.equals("mem_pn") && generalSort.equals("old")){
				
				//반복예약 '예약자 핸드폰 번호'옵션 검색, 오래된 순 정렬
				int totalCount = searchDao.rownumRepeatSelectByMemPN(searchCont);
				PagingBean pageBean = new PagingBean(totalCount, page);	
				
				searchInfo.put("value", searchCont);
				searchInfo.put("startPage", pageBean.getBeginItemInPage());
				searchInfo.put("endPage", pageBean.getEndItemInPage());
				
				System.out.println("Sort : "+generalSort+", value : "+searchCont+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage());

				repeatSearchResult =  searchDao.repeatSelectByMemPNOrderByOldList(searchInfo);

				map.put("pageBean", pageBean);
				map.put("searchResult", repeatSearchResult);
				
			}		
			
			
			System.out.println("a"+repeatSearchResult);
			

		}
		
		System.out.println("Service generalPage : " +page+ ", generalSort : " +generalSort);

		
		return map;
	}
	
	//상세내역 출력
	public  ArrayList<HashMap<String, Object>> showReservDetail(int repeatNo) {
		
		int repeatSeq = repeatNo;
		
		ArrayList<HashMap<String, Object>> searchDetailResult = new ArrayList<HashMap<String, Object>>();
		searchDetailResult = searchDao.repeatSearchDetailContents(repeatSeq);
		
		System.out.println("Service Search Detail Result : " + searchDetailResult);
		
		
		return searchDetailResult;
		
	}
	
	
	
	//자동완성 Service
	public void formAutoComplete(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<HashMap<String, Object>> autocompleteList = new ArrayList<HashMap<String, Object>>();
		
		String selectOption = request.getParameter("selectSearchOpt");
		String inputValue = request.getParameter("value");
		System.out.println("Controller Auto Complete : " + selectOption+", Input Value : "+inputValue);
		
		if(request.getParameter("selectSearchOpt")!=null){
			
			if(selectOption.equals("all")) {
				autocompleteList =  searchDao.autocompleteByAllList(inputValue);
			}else if(selectOption.equals("title")) {
				autocompleteList = searchDao.autocompleteByTitleList(inputValue);
			}else if(selectOption.equals("mem_nm")) {
				autocompleteList = searchDao.autocompleteByMemNMList(inputValue);
			}else if(selectOption.equals("mem_pn")) {
				autocompleteList = searchDao.autocompleteByMemPNList(inputValue);
			}
			
			System.out.println("a"+autocompleteList);
		}
		
		
		//ArratList를 JSON에 입력
		JSONArray jarr = new JSONArray();
		try{
			for(int i=0 ; i < autocompleteList.size() ; i++) {
				JSONObject sOjbt = new JSONObject();
				
				//문자열 자르기
				String attr = autocompleteList.get(i).toString();
				String arr1[] = attr.split("=");
				String arr2[] = arr1[1].split("}");
				
				//JSON에 입력
				sOjbt.put("data", arr2[0]);

				jarr.put(sOjbt);
			}
		}catch (JSONException e) {
			e.printStackTrace();
			System.out.println("JSON변환에러");
		}
		
		System.out.println(jarr);
		
		
		//PrintWriter로 출력
		try {
			PrintWriter result = response.getWriter();
			result.print(jarr);
			result.flush();
			result.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("PrintWriter 에러");
		}
		
	}
	
	//index.jsp에서 사용해야 하는 자동완성 기능, 전화번호 자동완성
	public void mempnAutoComplete(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<HashMap<String, Object>> autocompleteList = new ArrayList<HashMap<String, Object>>();
		
		//inputValue : 입력받을 전화번호
		String inputValue = request.getParameter("value");
		System.out.println("Controller Auto Complete Input Value : "+inputValue);
		
		autocompleteList = searchDao.autocompleteByMemPNForIndexList(inputValue);
			
		System.out.println("a"+autocompleteList);

		
		//ArratList를 JSON에 입력
		JSONArray jarr = new JSONArray();
		try{
			for(int i=0 ; i < autocompleteList.size() ; i++) {
				JSONObject sOjbt = new JSONObject();
				
				//문자열 자르기
				String attr = autocompleteList.get(i).toString();
				String arr1[] = attr.split("=");
				String arr2[] = arr1[1].split("}");
				
				//JSON에 입력
				sOjbt.put("data", arr2[0]);

				jarr.put(sOjbt);
			}
		}catch (JSONException e) {
			e.printStackTrace();
			System.out.println("JSON변환에러");
		}
		
		System.out.println(jarr);
		

		//PrintWriter로 출력
		try {
			PrintWriter result = response.getWriter();
			result.print(jarr);
			result.flush();
			result.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("PrintWriter 에러");
		}
		
	}
	
	
	
	//관리자 예약내역, 일반예약 내역 출력, 시작일과 종료일로 검색
	public HashMap<String, Object> searchGeneralHistoryResult(HttpServletRequest request, int page, String sort, String sDate, String eDate) {
		
		ArrayList<HashMap<String, Object>> generalHistorySearchResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		
		HashMap<String, Object> dateInfo = new HashMap<String, Object>();
		dateInfo.put("startDate", sDate);
		dateInfo.put("endDate", eDate);
		

		int totalCount = searchDao.rownumSelectByDate(dateInfo);
		PagingBean pageBean = new PagingBean(totalCount, page);
		
		
		HashMap<String, Object> searchInfo =  new HashMap<String, Object>();
		searchInfo.put("startDate", sDate);
		searchInfo.put("endDate", eDate);
		searchInfo.put("startPage",  pageBean.getBeginItemInPage());
		searchInfo.put("endPage", pageBean.getEndItemInPage());
		
		System.out.println("Sort : "+sort+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage() + ", Total Count : " + totalCount );
		
		if(sort.equals("old")) {
			
			generalHistorySearchResult = searchDao.generalSelectByDateOrderByOldList(searchInfo);
			
			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", generalHistorySearchResult);
			
		}else if(sort.equals("new")) {
			
			generalHistorySearchResult = searchDao.generalSelectByDateOrderByNewList(searchInfo);
			
			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", generalHistorySearchResult);
			
		}else if(sort.equals("name")) {
			
			generalHistorySearchResult = searchDao.generalSelectByDateOrderByNameList(searchInfo);
			
			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", generalHistorySearchResult);
			
		}
		
		return map;
		
	}
	
	//관리자 예약내역, 반복예약 내역 출력, 시작일과 종료일로 검색
	public HashMap<String, Object> searchRepeatHistoryResult(HttpServletRequest request, int page, String sort, String sDate, String eDate) {
		
		ArrayList<HashMap<String, Object>> repeatHistorySearchResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		
		HashMap<String, Object> dateInfo = new HashMap<String, Object>();
		dateInfo.put("startDate", sDate);
		dateInfo.put("endDate", eDate);
		

		int totalCount = searchDao.rownumRepeatSelectByDate(dateInfo);
		PagingBean pageBean = new PagingBean(totalCount, page);
		
		
		HashMap<String, Object> searchInfo =  new HashMap<String, Object>();
		searchInfo.put("startDate", sDate);
		searchInfo.put("endDate", eDate);
		searchInfo.put("startPage",  pageBean.getBeginItemInPage());
		searchInfo.put("endPage", pageBean.getEndItemInPage());
		
		System.out.println("Sort : "+sort+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage() + ", Total Count : " + totalCount );
		
		if(sort.equals("old")) {
			
			repeatHistorySearchResult = searchDao.repeatSelectByDateOrderByOldList(searchInfo);
			
			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", repeatHistorySearchResult);
			
			
			
		}else if(sort.equals("new")) {
			
			repeatHistorySearchResult = searchDao.repeatSelectByDateOrderByNewList(searchInfo);
			
			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", repeatHistorySearchResult);
			
		}else if(sort.equals("name")) {
			
			repeatHistorySearchResult = searchDao.repeatSelectByDateOrderByNameList(searchInfo);
			
			map.put("pageBean", pageBean);
			map.put("repeatHistorySearchResult", repeatHistorySearchResult);
			
		}
		
		System.out.println("Repeat History Service : " + repeatHistorySearchResult);
		
		return map;
		
	}
	
	//관리자 예약내역 중 상세내역 출력
	public  ArrayList<HashMap<String, Object>> showHistDetail(int repeatNo) {
			
			int repeatSeq = repeatNo;
			
			ArrayList<HashMap<String, Object>> searchDetailResult = new ArrayList<HashMap<String, Object>>();
			searchDetailResult = searchDao.repeatSearchDetailContentsInHistory(repeatSeq);
			
			System.out.println("Service Search Detail Result : " + searchDetailResult);
			
			
			return searchDetailResult;
			
		}
	
	//관리자 예약내역, 노쇼 목록 출력, 시작일과 종료일로 검색
	public HashMap<String, Object> searchNoshowHistoryResult(HttpServletRequest request, int page, String sort, String sDate, String eDate) {
		
		ArrayList<HashMap<String, Object>> noshowHistorySearchResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map =  new HashMap<String, Object>();
		
		
		HashMap<String, Object> dateInfo = new HashMap<String, Object>();
		dateInfo.put("startDate", sDate);
		dateInfo.put("endDate", eDate);
		

		int totalCount = searchDao.rownumNoshowSelectByDate(dateInfo);
		PagingBean pageBean = new PagingBean(totalCount, page);
		
		
		HashMap<String, Object> searchInfo =  new HashMap<String, Object>();
		searchInfo.put("startDate", sDate);
		searchInfo.put("endDate", eDate);
		searchInfo.put("startPage",  pageBean.getBeginItemInPage());
		searchInfo.put("endPage", pageBean.getEndItemInPage());
		
		System.out.println("Sort : "+sort+", : startPage : "+pageBean.getBeginItemInPage()+", endPage : "+pageBean.getEndItemInPage() + ", Total Count : " + totalCount );
		
		if(sort.equals("old")) {
			
			noshowHistorySearchResult = searchDao.noshowSelectByDateOrderByOldList(searchInfo);
			
			map.put("pageBean", pageBean);
			map.put("noshowHistorySearchResult", noshowHistorySearchResult);
			
		}else if(sort.equals("new")) {
			
			noshowHistorySearchResult = searchDao.noshowSelectByDateOrderByNewList(searchInfo);
			
			map.put("pageBean", pageBean);
			map.put("noshowHistorySearchResult", noshowHistorySearchResult);
			
		}else if(sort.equals("name")) {
			
			noshowHistorySearchResult = searchDao.noshowSelectByDateOrderByNameList(searchInfo);
			
			map.put("pageBean", pageBean);
			map.put("noshowHistorySearchResult", noshowHistorySearchResult);
			
		}
		
		System.out.println("Repeat History Service : " + noshowHistorySearchResult);
		
		return map;
		
	}
	
	
}