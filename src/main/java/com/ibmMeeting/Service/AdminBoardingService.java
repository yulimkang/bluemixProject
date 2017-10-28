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
		System.out.println(boardingDao.offBoardingListAll());
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
	
	
	public ArrayList<HashMap<String,Object>> noShowUserList(){
		return boardingDao.noShowUserList();
	}
	
	public ArrayList<HashMap<String,Object>> noShowReservitonList(HttpServletRequest request){
		
		String wantDate;
		if(request.getParameter("wantDate")==null){
			wantDate = commonService.nowTime();
		}
		else{
			wantDate = request.getParameter("wantDate");
		}
		
		return boardingDao.noShowReservationList(wantDate);
		
	}
	
	
	public void noShowValueSetting(HttpServletRequest request){
		
		String noShowBtnType = request.getParameter("noShowBtnType");
		Integer memberSeq = Integer.parseInt(request.getParameter("noShowUserSeq"));
		
		boardingDao.noShowCountInit(memberSeq);
		System.out.println("no-Show-Update-error");
	}
	
	public void memberBan(HttpServletRequest request){
		
		Integer memberSeq = Integer.parseInt(request.getParameter("memberSeq"));
		boardingDao.memeberBlock(memberSeq);
		
	}
	
	public String memberBanCheck(HttpServletRequest request){
		
		String memberPn = request.getParameter("memberPn");
		return boardingDao.memberBanCheck(memberPn);
	}

	/* 
	 * 작성자 : 고창환
	 * 오프보딩 온보딩 검색 결과저장
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
