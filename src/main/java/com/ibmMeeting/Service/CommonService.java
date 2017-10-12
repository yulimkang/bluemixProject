package com.ibmMeeting.Service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ibmMeeting.Constant.ConstantCode;

@Service
public class CommonService {
	
	
	public String sessionCheck(HttpSession session){
		
		if(session.getAttribute("id")==null){
			return ConstantCode.FAIL_STRING;
		}
		else{
			return ConstantCode.SUCCESS_STRING;
		}
	}
	
	public void logout(HttpSession session){
		session.invalidate();
	}

}
