/*
 * 작성자 : 최문정
 * 내용 : 관리자 예약내역 페이지 Controller
 */

package com.ibmMeeting.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.ibmMeeting.Service.AdminReservationHistoryService;

@Controller
public class AdminReservationHistoryController {
	
	@Autowired
	private AdminReservationHistoryService adminRsvHistService;
	
}
