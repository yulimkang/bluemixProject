package com.ibmMeeting.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface SearchDao {
	
	/**
	 * 작성자 : 최문정
	 * 내용 :  일반예약 검색, 최신순, 전체/회의제목/예약자/전화번호 옵션
	 * @param searchInfo
	 * @return
	 */
	ArrayList<HashMap<String,Object>> generalSelectByAllOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByTitleOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByMemNMOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> genelralSelectByMemPNOrderByNewList(HashMap<String,Object> searchInfo);
	
	/**
	 * 작성자 : 최문정
	 * 내용 :  일반예약 검색, 오래된 순, 전체/회의제목/예약자/전화번호 옵션
	 * @param searchInfo
	 * @return
	 */
	ArrayList<HashMap<String,Object>> generalSelectByAllOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByTitleOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByMemNMOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> genelralSelectByMemPNOrderByOldList(HashMap<String,Object> searchInfo);
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 페이징 처리를 위한 검색 결과 count
	 * @param searchCont
	 * @return
	 */
	Integer rownumSelectByAll(String searchCont);
	Integer rownumSelectByTitle(String searchCont);
	Integer rownumSelectByMemNM(String searchCont);
	Integer rownumSelectByMemPN(String searchCont);
	
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 반복예약 검색, 최신순, 전체/회의제목/예약자/전화번호 옵션
	 * @param searchInfo
	 * @return
	 */
	ArrayList<HashMap<String,Object>> repeatSelectByAllOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByTitleOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByMemNMOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByMemPNOrderByNewList(HashMap<String,Object> searchInfo);
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 반복예약 검색, 오래된 순, 전체/회의제목/예약자/전화번호 옵션
	 * @param searchInfo
	 * @return
	 */
	ArrayList<HashMap<String,Object>> repeatSelectByAllOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByTitleOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByMemNMOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByMemPNOrderByOldList(HashMap<String,Object> searchInfo);
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 페이징 처리를 위한 반복예약 count
	 * @param searchCont
	 * @return
	 */
	Integer rownumRepeatSelectByAll(String searchCont);
	Integer rownumRepeatSelectByTitle(String searchCont);
	Integer rownumRepeatSelectByMemNM(String searchCont);
	Integer rownumRepeatSelectByMemPN(String searchCont);
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 반복예약내역 상세보기(모달)
	 * @param repeatSeq
	 * @return
	 */
	ArrayList<HashMap<String,Object>> repeatSearchDetailContents(int repeatSeq);

	
	/**
	 * 작성자 : 최문정
	 * 내용 : 자동완성기능
	 * @param searchCont
	 * @return
	 */
	ArrayList<HashMap<String,Object>> autocompleteByAllList(String searchCont);
	ArrayList<HashMap<String,Object>> autocompleteByTitleList(String searchCont);
	ArrayList<HashMap<String,Object>> autocompleteByMemNMList(String searchCont);
	ArrayList<HashMap<String,Object>> autocompleteByMemPNList(String searchCont);

	

	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 '예약내역' 시작일과 종료일로 검색(History Table), 일반예약, 최신순/오래된순/예약자명 가나다순
	 * @param searchInfo
	 * @return
	 */
	ArrayList<HashMap<String,Object>> generalSelectByDateOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByDateOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> generalSelectByDateOrderByNameList(HashMap<String,Object> searchInfo);
	
	/**
	 * 작성자 : 최문정
	 * 내용 :  : 관리자 '예약내역' 시작일과 종료일로 검색(History Table), 페이징 처리를 위한 결과 count
	 * @param dateInfo
	 * @return
	 */
	Integer rownumSelectByDate(HashMap<String,Object> dateInfo);
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 일반예약 '예약내역' 시작일과 종료일로 검색(History Table), 반복예약, 최신순/오래된순/예약자명 가나다순
	 * @param searchInfo
	 * @return
	 */
	ArrayList<HashMap<String,Object>> repeatSelectByDateOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByDateOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> repeatSelectByDateOrderByNameList(HashMap<String,Object> searchInfo);
			
	/**
	 * 작성자 : 최문정
	 * 내용 :  : 관리자 반복예약 '예약내역' 시작일과 종료일로 검색(History Table), 페이징 처리를 위한 결과 count
	 * @param dateInfo
	 * @return
	 */
	Integer rownumRepeatSelectByDate(HashMap<String,Object> dateInfo);
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 반복예약 '예약내역' 시작일과 종료일로 검색(History Table), 상세내역 출력
	 * @param repeatSeq
	 * @return
	 */
	ArrayList<HashMap<String,Object>> repeatSearchDetailContentsInHistory(int repeatSeq);
	
	
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 '예약내역' 시작일과 종료일로 검색, NOSHOW 회의목록, 최신순/오래된순/예약자가나다순
	 * @param searchInfo
	 * @return
	 */
	ArrayList<HashMap<String,Object>> noshowSelectByDateOrderByNewList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> noshowSelectByDateOrderByOldList(HashMap<String,Object> searchInfo);
	ArrayList<HashMap<String,Object>> noshowSelectByDateOrderByNameList(HashMap<String,Object> searchInfo);
		
	/**
	 * 작성자 : 최문정
	 * 내용 : 관리자 '예약내역' 시작일과 종료일로 검색, NOSHOW 회의목록 페이징을 위한 count
	 * @param dateInfo
	 * @return
	 */
	Integer rownumNoshowSelectByDate(HashMap<String,Object> dateInfo);
	
	
}
