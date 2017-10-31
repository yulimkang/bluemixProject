package com.ibmMeeting.Service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.BoardingDao;
import com.ibmMeeting.paging.util.PagingBean;

@Service
public class AdminBoardingService {

	@Autowired
	BoardingDao boardingDao;
	
	@Autowired
	CommonService commonService;
	
	
	/* 
	 * 작성자 : 고창환
	 * 오프보딩 온보딩 전체리스트
	 */
	public ArrayList<HashMap<String,Object>> BoardingAll() {
		// TODO Auto-generated method stub
		return boardingDao.BoardingListAll();
	}
	
	public ArrayList<HashMap<String,Object>> offBoardingAll() {
		// TODO Auto-generated method stub
		return boardingDao.offBoardingListAll();
	}

	
	
	/* 
	 * 작성자 : 고창환
	 * 오프보딩 온보딩 멤버 정보 수정
	 */
	public String memberUpdate(HttpServletRequest request){
		
		String memberName=request.getParameter("updateName");
		String memberPn=request.getParameter("updatePhone");
		String memberEm=request.getParameter("updateEmail");
		String memberComp="AMORE";
		String memberState=request.getParameter("updateState");
		
		HashMap<String,Object> memberUpdate = new HashMap<String,Object>();
		memberUpdate.put("memberName", memberName);
		memberUpdate.put("memberPn", memberPn);
		memberUpdate.put("memberEm", memberEm);
		memberUpdate.put("memberComp", memberComp);
		memberUpdate.put("memberState", memberState);
		
		boardingDao.memberUpdate(memberUpdate);

		return "success";
	}
	
	/* 
	 * 작성자 : 박성준
	 * noShow 한번이상이라도 한 회원 리스트 출력
	 */
	public ArrayList<HashMap<String,Object>> noShowUserList(){
		return boardingDao.noShowUserList();
	}
	/* 
	 * 작성자 : 박성준
	 * noShow된 예약 리스트 출력
	 */
	public ArrayList<HashMap<String,Object>> noShowReservitonList(HttpServletRequest request){
		
		String wantDate;
		// wantDate를 받아오지 않는 상황이라면 현재시간

		if(request.getParameter("wantDate")==null){
			wantDate = commonService.nowTime();
		}
		else{
			wantDate = request.getParameter("wantDate");
		}
		
		return boardingDao.noShowReservationList(wantDate);
		
	}
	
	/* 
	 * 작성자 : 박성준
	 * 특정사용자 No-Show Count 초기화
	 * 유저Sequence넘버를 통해 noShowCount 초기화
	 */
	public void noShowValueSetting(HttpServletRequest request){
		
		Integer memberSeq = Integer.parseInt(request.getParameter("noShowUserSeq"));
		boardingDao.noShowCountInit(memberSeq);
	}
	/* 
	 * 작성자 : 박성준
	 * 특정사용자 No-Show 밴
	 * 유저Sequence넘버를 통해 noShowBan
	 */
	public void memberBan(HttpServletRequest request){
		
		Integer memberSeq = Integer.parseInt(request.getParameter("memberSeq"));
		boardingDao.memeberBlock(memberSeq);
		
	}
	/* 
	 * 작성자 : 박성준
	 * 사용자 차단상태 확인
	 * 핸드폰번호를 통해 사용자 차단상태 확인
	 */
	public String memberBanCheck(HttpServletRequest request){
		
		String memberPn = request.getParameter("memberPn");
		return boardingDao.memberBanCheck(memberPn);
	}

	/* 
	 * 작성자 : 고창환
	 * 온보딩 검색 결과저장
	 */
	public ArrayList<HashMap<String, Object>> searchResult(HttpServletRequest request) {
		
		
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> searchResult = new ArrayList<HashMap<String, Object>>();
		
		if(request.getParameter("searchOption")!=null){
			String selectOpt = request.getParameter("searchOption");
			String searchCont = request.getParameter("searchInputBox");
			
			
			if(selectOpt.equals("mem_name")) {
				searchResult =  boardingDao.BoardingListName(searchCont);
			}else if(selectOpt.equals("mem_pn")) {
				searchResult = boardingDao.BoardingListNumber(searchCont);
			}
		
		}
		return searchResult;
		
	}
	/* 
	 * 작성자 : 고창환
	 * 오프보딩 검색 결과저장
	 */
	public ArrayList<HashMap<String, Object>> offSearchResult(HttpServletRequest request) {
		
		
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> searchResult = new ArrayList<HashMap<String, Object>>();
		
		if(request.getParameter("searchOption")!=null){
			String selectOpt = request.getParameter("searchOption");
			String searchCont = request.getParameter("searchInputBox");
			
			
			if(selectOpt.equals("mem_name")) {
				searchResult =  boardingDao.offBoardingListName(searchCont);
			}else if(selectOpt.equals("mem_pn")) {
				searchResult = boardingDao.offBoardingListNumber(searchCont);
			}
			
		}
		return searchResult;
		
	}

	/* 
	 * 작성자 : 고창환
	 * 온보딩 페이징 처리
	 */
	public HashMap<String, Object> boardingPage(HttpServletRequest request,
			int page) {
		
		ArrayList<HashMap<String, Object>> searchResult = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String,Object> pageInfo = new HashMap<String,Object>();
		HashMap<String, Object> map =  new HashMap<String, Object>();


		int totalCount = boardingDao.rownumBoarding();
		PagingBean pageBean = new PagingBean(totalCount, page);	
		
		pageInfo.put("startPage", pageBean.getBeginItemInPage());
		pageInfo.put("endPage", pageBean.getEndItemInPage());

		searchResult =  boardingDao.boardingList(pageInfo);

		map.put("pageBean", pageBean);
		map.put("searchResult", searchResult);
		
		System.out.println("totalCount : "+totalCount + "Service Result : " + searchResult);

		return map;
	}
	

	
	
}
