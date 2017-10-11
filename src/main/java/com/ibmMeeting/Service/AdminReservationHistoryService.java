/*
 * 작성자 : 최문정
 * 내용 : 관리자 예약내역 페이지 Service
 */

package com.ibmMeeting.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.AdminDao;

@Service
public class AdminReservationHistoryService {
	
	@Autowired
	AdminDao adminDao;
	

}
