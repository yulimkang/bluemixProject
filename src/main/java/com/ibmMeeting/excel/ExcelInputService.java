package com.ibmMeeting.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	public HashMap<String, Object> historyExcelInput(HttpServletRequest request) {
		
		String wantDate = request.getParameter("histDeleteDate");

		ArrayList<HashMap<String, Object>> reservationList = reservationDao.selectAllReservationByDate(wantDate);
		ArrayList<HashMap<String, Object>> historyList = historyDao.selectAllHistoryByDate(wantDate);
		HashMap<String, Object> model = new HashMap<String, Object>();
		List<String> rsvHeaders = new ArrayList<String>();

		List<List<String>> rsvResults = new ArrayList<List<String>>();

		model.put("rsvSheetName", "예약내역");

		rsvHeaders.add("HistoryNo");
		rsvHeaders.add("회의제목");
		rsvHeaders.add("회의날짜");
		rsvHeaders.add("회의시작시간");
		rsvHeaders.add("회의종료시간");
		rsvHeaders.add("총회의시간");
		rsvHeaders.add("회의실");
		rsvHeaders.add("예약자 이름");
		rsvHeaders.add("예약자 핸드폰 번호");
		rsvHeaders.add("예약자 이메일");
		rsvHeaders.add("예약 비밀번호");
		rsvHeaders.add("회의 등록/수정일");
		rsvHeaders.add("예약상태");
		rsvHeaders.add("이메일 전송");
		rsvHeaders.add("반복예약기간");
		rsvHeaders.add("반복Seq");

		model.put("rsvHeaders", rsvHeaders);

		for (int i = 0; i < reservationList.size(); i++) {

			List<String> cell = new ArrayList<String>();

			cell.add(reservationList.get(i).get("RSV_NO").toString());
			cell.add(reservationList.get(i).get("RSV_TITLE").toString());
			cell.add(reservationList.get(i).get("RSV_DATE").toString());
			cell.add(reservationList.get(i).get("RSV_START_TIME").toString()
					.substring(0, 5));
			cell.add(reservationList.get(i).get("RSV_END_TIME").toString()
					.substring(0, 5));
			cell.add(reservationList.get(i).get("RSV_TOTAL_TIME").toString()
					.substring(0, 5));
			cell.add(reservationList.get(i).get("CONF_NM").toString());
			cell.add(reservationList.get(i).get("RSV_MEM_NM").toString());
			cell.add(reservationList.get(i).get("RSV_MEM_PN").toString());
			cell.add(reservationList.get(i).get("RSV_MEM_EM").toString());
			cell.add(reservationList.get(i).get("RSV_DEL_PWD").toString());
			cell.add(reservationList.get(i).get("RSV_REG_DATE").toString());
			cell.add(reservationList.get(i).get("RSV_CONFIRM_STATE").toString());
			cell.add(reservationList.get(i).get("RSV_EMAIL_CHECK").toString());
			cell.add(reservationList.get(i).get("RSV_REPEAT_PERIOD").toString());
			cell.add(reservationList.get(i).get("RSV_REPEAT_NO").toString());

			rsvResults.add(cell);
		}

		model.put("rsvResults", rsvResults);

		List<String> hstHeaders = new ArrayList<String>();
		List<List<String>> hstResults = new ArrayList<List<String>>();

		model.put("hstSheetName", "예약기록");

		hstHeaders.add("HistoryNo");
		hstHeaders.add("회의제목");
		hstHeaders.add("회의날짜");
		hstHeaders.add("회의시작시간");
		hstHeaders.add("회의종료시간");
		hstHeaders.add("총회의시간");
		hstHeaders.add("회의실");
		hstHeaders.add("예약자 이름");
		hstHeaders.add("예약자 핸드폰 번호");
		hstHeaders.add("예약자 이메일");
		hstHeaders.add("예약 비밀번호");
		hstHeaders.add("회의 등록/수정일");
		hstHeaders.add("예약상태");
		hstHeaders.add("반복예약기간");
		hstHeaders.add("반복Seq");

		model.put("hstHeaders", hstHeaders);

		for (int i = 0; i < historyList.size(); i++) {

			List<String> cell = new ArrayList<String>();

			cell.add(historyList.get(i).get("HST_NO").toString());
			cell.add(historyList.get(i).get("HST_RSV_TITLE").toString());
			cell.add(historyList.get(i).get("HST_DATE").toString());
			cell.add(historyList.get(i).get("HST_START_TIME").toString()
					.substring(0, 5));
			cell.add(historyList.get(i).get("HST_END_TIME").toString()
					.substring(0, 5));
			cell.add(historyList.get(i).get("HST_TOTAL_TIME").toString()
					.substring(0, 5));
			cell.add(historyList.get(i).get("CONF_NM").toString());
			cell.add(historyList.get(i).get("HST_RSV_MEM_NM").toString());
			cell.add(historyList.get(i).get("HST_RSV_MEM_PN").toString());
			cell.add(historyList.get(i).get("HST_RSV_MEM_EM").toString());
			cell.add(historyList.get(i).get("HST_DEL_PWD").toString());
			cell.add(historyList.get(i).get("HST_REG_DATE").toString());
			cell.add(historyList.get(i).get("HST_RSV_STATE").toString());
			cell.add(historyList.get(i).get("HST_REPEAT_PERIOD").toString());
			cell.add(historyList.get(i).get("HST_REPEAT_NO").toString());

			hstResults.add(cell);
		}
		model.put("hstResults", hstResults);

		return model;

	}

}
