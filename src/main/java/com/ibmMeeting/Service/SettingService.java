package com.ibmMeeting.Service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.AdminDao;
import com.ibmMeeting.Dao.SettingDao;

@Service
public class SettingService {
	
	@Autowired
	SettingDao settingDao;
	
	@Autowired
	AdminDao adminDao;
	
	/* 
	 * 작성자 : 박성준
	 * 셋팅값 불러오기
	 */
	public HashMap<String,Object> settingLoad(){
		
		return settingDao.settingLoad();
	}
	
	/* 
	 * 작성자 : 박성준
	 * 셋팅값 설정
	 */
	public void settingSubmit(HttpServletRequest request){
		
		// 사용자가 선택한 셋팅 제목? 
		String selectSetting = request.getParameter("selectSetting");
		
		// 사용자가 입력한 셋팅 값
		Integer settingValue = Integer.parseInt(request.getParameter("settingValue"));
		
		HashMap<String,Object> setting = new HashMap<String,Object>();
		
		setting.put("selectSetting", selectSetting);
		setting.put("settingValue", settingValue);
		
		settingDao.settingUpdate(setting);
		
	}
	
	/* 
	 * 작성자 : 박성준
	 * 사용자의 세션정보를 받아와 비밀번호 변경
	 */
	public void passwordChange(HttpServletRequest request,HttpSession session){
		// session value
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		
		HashMap<String,Object> loginInformation = new HashMap<String,Object>();
		
		loginInformation.put("id",id);
		loginInformation.put("pw",pw);
		
		adminDao.changePassword(loginInformation);
	}
	
	/* 
	 * 작성자 : 박성준
	 * 사용자의 세션정보를 받아와 E-Mail 변경
	 */
	public void emailChange(HttpServletRequest request,HttpSession session){
		//sessionValue
		String id = (String)session.getAttribute("id");
		String email = request.getParameter("email");
		
		HashMap<String,Object> adminInformation = new HashMap<String,Object>();
		
		adminInformation.put("id",id);
		adminInformation.put("email",email);
		
		adminDao.changeEmail(adminInformation);
	}

}
