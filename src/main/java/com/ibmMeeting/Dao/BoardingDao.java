package com.ibmMeeting.Dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardingDao {
	
	/* 
	 * 작성자 : 고창환
	 * 오프보딩 온보딩 특정 리스트
	 */
	ArrayList<HashMap<String,Object>> offBoardingListAll();//오프보딩 전체리스트
	ArrayList<HashMap<String,Object>> BoardingListAll();//온보딩 전체리스트
	void memberUpdate(HashMap<String,Object> memberUpdate);//온보딩,오프보딩 멤버정보 수정
	
	ArrayList<HashMap<String,Object>> BoardingListName(String searchInputBox);//온보딩 이름으로 검색
	ArrayList<HashMap<String,Object>> offBoardingListName(String searchInputBox);//오프보딩 이름으로 검색
	ArrayList<HashMap<String,Object>> BoardingListNumber(String searchInputBox);//온보딩 폰번호로 검색
	ArrayList<HashMap<String,Object>> offBoardingListNumber(String searchInputBox);//오프보딩 폰번호로 검색

	
	


	
	/* 
	 * 작성자 : 박성준
	 * No-Show
	 */
	
	ArrayList<HashMap<String,Object>> noShowUserList();					// No-Show 유저 리스트 
	ArrayList<HashMap<String,Object>> noShowReservationList();			// No-Show 회의 리스트
	ArrayList<HashMap<String,Object>> noShowDetail(HashMap<String,Object> noShowDetailInformation); // 특정 사용자가 NoShow한 예약 리스트 출력
	
	ArrayList<HashMap<String,Object>> noShowReservationList(String wantDate);						// 특정 날짜 noShowList
	void noShowCountInit(Integer memberSeq);							// 특정 사용자 No-Show Count 초기화
	void noShowAllUserInit();											// 모든 사용자 No-Show Count 초기화
	Integer noShowUserCount(HashMap<String,Object> memberInformation);	// 특정 사용자의 No-Show Count
	
	void memberBan(HashMap<String,Object> memberInformation);			// 특정 사용자 No-Show 차단
	String memberBanCheck(String memberPn);								// 특정 사용자 No-Show 또는 차단 체크
	
	void noShowBanReset(String today);									// 차단 마지막 날 NoShow 차단 해제
	void memeberBlock(Integer memberSeq);									// No-ShowCount 설정값 이상으로 차단
	void noShowCountPlusInManage(HashMap<String,Object> memberInformation); // No-Show 카운트 증가
	
	void noShowCountMinusInReservation(HashMap<String,Object> updateInfo);
	
	int rownumByMember();
	
	//페이지 개수만큼 내용을 가져오는 함수
	ArrayList<HashMap<String,Object>> boardingList(HashMap<String,Object> searchInfo);
	//전체 로우넘 카운트
	Integer rownumBoarding();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
