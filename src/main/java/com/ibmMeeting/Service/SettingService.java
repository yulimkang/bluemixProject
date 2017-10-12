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
	
	public HashMap<String,Object> settingLoad(){
		
		return settingDao.settingLoad();
	}
	
	public void settingSubmit(HttpServletRequest request){
		
		String selectSetting = request.getParameter("selectSetting");
		
		Integer settingValue = Integer.parseInt(request.getParameter("settingValue"));
		
		HashMap<String,Object> setting = new HashMap<String,Object>();
		
		setting.put("selectSetting", selectSetting);
		setting.put("settingValue", settingValue);
		
		settingDao.settingUpdate(setting);
		
	}
	
	public void passwordChange(HttpServletRequest request,HttpSession session){
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		
		HashMap<String,Object> loginInformation = new HashMap<String,Object>();
		
		loginInformation.put("id",id);
		loginInformation.put("pw",pw);
		
		adminDao.changePassword(loginInformation);
	}

}
