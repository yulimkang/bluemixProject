package com.ibmMeeting.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface SearchDao {
	
	//일반예약 검색 기능(최신 날짜 순)
	ArrayList<HashMap<String,Object>> generalSelectByAllOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByTitleOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByMemNMOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> genelralSelectByMemPNOrderByNewList(HashMap<String,Object> searchInfo);
	
	//일반예약 검색 기능(오래된 순)	
	ArrayList<HashMap<String,Object>> generalSelectByAllOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByTitleOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByMemNMOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> genelralSelectByMemPNOrderByOldList(HashMap<String,Object> searchInfo);
	
	//일반예약 검색 결과 개수
	Integer rownumSelectByAll(String searchCont);
	Integer rownumSelectByTitle(String searchCont);
	Integer rownumSelectByMemNM(String searchCont);
	Integer rownumSelectByMemPN(String searchCont);
	
	
	
	//반복예약 검색 기능(최신 날짜 순)
	ArrayList<HashMap<String,Object>> repeatSelectByAllOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByTitleOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByMemNMOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByMemPNOrderByNewList(HashMap<String,Object> searchInfo);
	
	//반복예약 검색 기능(오래된 순)
	ArrayList<HashMap<String,Object>> repeatSelectByAllOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByTitleOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByMemNMOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByMemPNOrderByOldList(HashMap<String,Object> searchInfo);
	
	//반복예약 검색 결과 개수
	Integer rownumRepeatSelectByAll(String searchCont);
	Integer rownumRepeatSelectByTitle(String searchCont);
	Integer rownumRepeatSelectByMemNM(String searchCont);
	Integer rownumRepeatSelectByMemPN(String searchCont);
	
	//반복예약 상세내역 출력
	ArrayList<HashMap<String,Object>> repeatSearchDetailContents(int repeatSeq);
	
	
	//반복예약 상세내역 출력 개수
	//Integer rownumRepeatSearchDetailContents(String searchCont);
	
	
	//가예약 검색 기능(최신 날짜 순)
	ArrayList<HashMap<String,Object>> fakeSelectByAllOrderByNewList(String searchCont);
	ArrayList<HashMap<String,Object>> fakeSelectByTitleOrderByNewList(String searchCont);
	ArrayList<HashMap<String,Object>> fakeSelectByMemNMOrderByNewList(String searchCont);
	ArrayList<HashMap<String,Object>> fakeSelectByMemPNOrderByNewList(String searchCont);
	
	
	//가예약 검색 기능(오래된 순)
	ArrayList<HashMap<String,Object>> fakeSelectByAllOrderByOldList(String searchCont);
	ArrayList<HashMap<String,Object>> fakeSelectByTitleOrderByOldList(String searchCont);
	ArrayList<HashMap<String,Object>> fakeSelectByMemNMOrderByOldList(String searchCont);
	ArrayList<HashMap<String,Object>> fakeSelectByMemPNOrderByOldList(String searchCont);
	
	
	
	//자동완성기능
	ArrayList<HashMap<String,Object>> autocompleteByAllList(String searchCont);
	ArrayList<HashMap<String,Object>> autocompleteByTitleList(String searchCont);
	ArrayList<HashMap<String,Object>> autocompleteByMemNMList(String searchCont);
	ArrayList<HashMap<String,Object>> autocompleteByMemPNList(String searchCont);
	
	//전화번호 자동완성 기능
	ArrayList<HashMap<String,Object>> autocompleteByMemPNForIndexList(String searchCont);
	
	
	
	
	
	
	//관리자 예약내역 검색(History 테이블)
	//일반예약
	//일반예약 최신순
	ArrayList<HashMap<String,Object>> generalSelectByDateOrderByNewList(HashMap<String,Object> searchInfo);
	
	//일반예약 오래된 순
	ArrayList<HashMap<String,Object>> generalSelectByDateOrderByOldList(HashMap<String,Object> searchInfo);
	
	//일반예약 예약자 가나다순
	ArrayList<HashMap<String,Object>> generalSelectByDateOrderByNameList(HashMap<String,Object> searchInfo);
	
	//일반예약 페이징을 위한 검색 결과 개수
	Integer rownumSelectByDate(HashMap<String,Object> dateInfo);
	
	//반복예약
	//반복예약 최신순
	ArrayList<HashMap<String,Object>> repeatSelectByDateOrderByNewList(HashMap<String,Object> searchInfo);
	
	//반복예약 오래된 순
	ArrayList<HashMap<String,Object>> repeatSelectByDateOrderByOldList(HashMap<String,Object> searchInfo);
	
	//반복예약 예약자 가나다순
	ArrayList<HashMap<String,Object>> repeatSelectByDateOrderByNameList(HashMap<String,Object> searchInfo);
			
	//반복예약 페이징을 위한 검색 결과 개수
	Integer rownumRepeatSelectByDate(HashMap<String,Object> dateInfo);
	
	//반복예약 상세목록
	ArrayList<HashMap<String,Object>> repeatSearchDetailContentsInHistory(int repeatSeq);
	
	
	//노쇼 최신순
	ArrayList<HashMap<String,Object>> noshowSelectByDateOrderByNewList(HashMap<String,Object> searchInfo);
	
	//노쇼 오래된 순
	ArrayList<HashMap<String,Object>> noshowSelectByDateOrderByOldList(HashMap<String,Object> searchInfo);
		
	//노쇼 예약자 가나다순
	ArrayList<HashMap<String,Object>> noshowSelectByDateOrderByNameList(HashMap<String,Object> searchInfo);
		
	//노쇼 페이징을 위한 검색 결과 개수
	Integer rownumNoshowSelectByDate(HashMap<String,Object> dateInfo);
	
	
}
