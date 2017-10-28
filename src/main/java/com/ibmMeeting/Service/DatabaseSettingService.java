package com.ibmMeeting.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.TestDao;

@Service
public class DatabaseSettingService {
	
	@Autowired
	TestDao testDao;
	
	public void encodingUpdate(){
		testDao.updateNames();
		testDao.updateServer();
	}
}
