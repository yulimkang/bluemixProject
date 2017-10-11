package com.ibmMeeting.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.SearchDao;

@Service
public class SearchService {
	
	@Autowired
	SearchDao searchDao;
	
	public ArrayList<HashMap<String, Object>> allReservList() {
		return searchDao.allReservList();
	}
	
}