package com.ibmMeeting.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.BoardingDao;

import java.util.*;

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

	
}
