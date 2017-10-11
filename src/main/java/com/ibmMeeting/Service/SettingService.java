package com.ibmMeeting.Service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.SettingDao;

@Service
public class SettingService {
	
	@Autowired
	SettingDao settingDao;
	
	public HashMap<String,Object> settingLoad(){
		
		return settingDao.settingLoad();
	}
	
	public void settingSubmit(HttpServletRequest request){
		
		String selectSetting = request.getParameter("selectSetting");
		System.out.println(selectSetting);
		
		Integer settingValue = Integer.parseInt(request.getParameter("settingValue"));
		System.out.println(settingValue);
		
		HashMap<String,Object> setting = new HashMap<String,Object>();
		
		setting.put("selectSetting", selectSetting);
		setting.put("settingValue", settingValue);
		
		settingDao.settingUpdate(setting);
		
	}

}
