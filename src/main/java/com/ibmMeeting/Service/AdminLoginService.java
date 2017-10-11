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

	public Integer loginCheck(HttpServletRequest request,HttpSession session){
		
		HashMap<String,Object> loginInformation = new HashMap<String,Object>();
		String id = request.getParameter("id");
		
		loginInformation.put("id", id);
		loginInformation.put("pw", request.getParameter("pw"));
		
		if(adminDao.loginCheck(loginInformation)>ConstantCode.ZERO){
			session.setAttribute("id", id);
			return ConstantCode.SUCCESS;
		}
		else{
			return ConstantCode.FAIL;
		}
	}

}
