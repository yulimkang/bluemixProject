package com.ibmMeeting.Service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.AdminDao;

@Service
public class AdminLoginService {
	
	@Autowired
	AdminDao adminDao;

	/* 
	 * 작성자 : 박성준
	 * 로그인 성공유무 Return
	 */
	public Integer loginCheck(HttpServletRequest request,HttpSession session){
		
		HashMap<String,Object> loginInformation = new HashMap<String,Object>();
		String id = request.getParameter("id");
		
		loginInformation.put("id", id);
		loginInformation.put("pw", request.getParameter("pw"));
		
		// admin 테이블에 입력값이 하나라도 있다면, 즉 로그인이 성공했다면 세션에 저장
		if(adminDao.loginCheck(loginInformation)>ConstantCode.ZERO){
			session.setAttribute("id", id);
			return ConstantCode.SUCCESS;
		}
		else{
			return ConstantCode.FAIL;
		}
	}

}
