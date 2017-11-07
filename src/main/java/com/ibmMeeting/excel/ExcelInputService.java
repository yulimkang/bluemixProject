package com.ibmMeeting.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.HistoryDao;
import com.ibmMeeting.Dao.ReservationDao;

@Service
public class ExcelInputService {
	
	@Autowired
	HistoryDao historyDao;
	
	@Autowired
	ReservationDao reservationDao;
	
	public HashMap<String,Object> excelInput(){
		
		ArrayList<HashMap<String, Object>> historyResult = historyDao.selectAllHistoryByDate("2017-11-07");
		ArrayList<HashMap<String, Object>> ReservationResult = reservationDao.selectAllReservationByDate("2017-11-07");
		
		HashMap<String,Object> model = new HashMap<String,Object>();
		List<String> headers = new ArrayList<String>();
		
		List<List<String>> results = new ArrayList<List<String>>();
		
		model.put("sheetname", "hello");
		
		headers.add("HistoryNo");
		headers.add("회의제목");
		headers.add("회의날짜");
		headers.add("회의시작시간");
		headers.add("회의종료시간");
		headers.add("총회의시간");
		headers.add("회의실");
		headers.add("예약자 이름");
		headers.add("예약자 핸드폰 번호");
		headers.add("예약자 이메일");
		headers.add("예약 비밀번호");
		headers.add("회의 등록/수정일");
		headers.add("예약상태");
		headers.add("반복예약기간");
		headers.add("반복주기설정");
		headers.add("반복Seq");
		
		model.put("headers", headers);
		
		System.out.println(historyResult.size());
		
		for(int i=0; i<historyResult.size(); i++){
			
			List<String> cell = new ArrayList<String>();
			
			cell.add(historyResult.get(i).get("HST_NO").toString());
			cell.add(historyResult.get(i).get("HST_RSV_TITLE").toString());
			cell.add(historyResult.get(i).get("HST_DATE").toString());
			cell.add(historyResult.get(i).get("HST_START_TIME").toString().substring(0, 5));
			cell.add(historyResult.get(i).get("HST_END_TIME").toString().substring(0, 5));
			cell.add(historyResult.get(i).get("HST_TOTAL_TIME").toString().substring(0, 5));
			cell.add(historyResult.get(i).get("CONF_NM").toString());
			cell.add(historyResult.get(i).get("HST_RSV_MEM_NM").toString());
			cell.add(historyResult.get(i).get("HST_RSV_MEM_PN").toString());
			cell.add(historyResult.get(i).get("HST_RSV_MEM_EM").toString());
			cell.add(historyResult.get(i).get("HST_DEL_PWD").toString());
			cell.add(historyResult.get(i).get("HST_REG_DATE").toString());
			cell.add(historyResult.get(i).get("HST_RSV_STATE").toString());
			cell.add(historyResult.get(i).get("HST_REPEAT_PERIOD").toString());
			cell.add(historyResult.get(i).get("HST_SETTING").toString());
			cell.add(historyResult.get(i).get("HST_REPEAT_NO").toString());
			results.add(cell);
		}
		
		System.out.println(results);
		
		model.put("results",results);
		
		return model;
		
	}

}
