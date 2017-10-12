package com.ibmMeeting.Service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.BoardingDao;

@Service
public class AdminBoardingService {

	@Autowired
	BoardingDao boardingDao;
	
	
	
	public ArrayList<HashMap<String,Object>> BoardingAll() {
		// TODO Auto-generated method stub
		return boardingDao.BoardingListAll();
	}
	

	
	
	public ArrayList<HashMap<String,Object>> BoardingName() {
		// TODO Auto-generated method stub
		return boardingDao.BoardingListName();
	}
	
	public ArrayList<HashMap<String,Object>> BoardingNumber() {
		// TODO Auto-generated method stub
		return boardingDao.BoardingListNumber();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<HashMap<String,Object>> noShowUserList(){
		return boardingDao.noShowUserList();
	}
	
	public void noShowValueSetting(HttpServletRequest request){
		
		String noShowBtnType = request.getParameter("noShowBtnType");
		Integer memberSeq = Integer.parseInt(request.getParameter("noShowUserSeq"));
		
		if(noShowBtnType.equals("plus")){
			boardingDao.noShowCountPlus(memberSeq);
		}
		else if(noShowBtnType.equals("minus")){
			boardingDao.noShowCountMinus(memberSeq);
		}
		else if(noShowBtnType.equals("init")){
			boardingDao.noShowCountInit(memberSeq);
		}
		else{
			System.out.println("no-Show-Update-error");
		}
	}
	
	public void memberBan(HttpServletRequest request){
		
		Integer memberSeq = Integer.parseInt(request.getParameter("memberSeq"));
		boardingDao.memeberBlock(memberSeq);
		
	}
	
}
