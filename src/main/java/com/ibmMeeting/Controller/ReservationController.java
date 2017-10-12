package com.ibmMeeting.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibmMeeting.Service.ReservationService;
import com.ibmMeeting.VO.Reservation;

@Controller
@RequestMapping("/Reservation")
public class ReservationController {

	
	@Autowired
	ReservationService reservationService;
	
	/**
	 * 예약된 이벤트들 달력으로 조회
	 * @return
	 */
	@RequestMapping("/GetReservation")
	@ResponseBody
	public List<Reservation> getReservation(){
		
		List<Reservation> list =  reservationService.getReservation();
		
		return list;
	}
	
	/**
	 * 예약된 이벤트 클릭시 상세 조회
	 * @return
	 */
	@RequestMapping("/GetReservationInfo")
	@ResponseBody
	public List<Reservation> getReservationInfo(@RequestParam int no){
		
		List<Reservation> list = reservationService.getReservationInfo(no);
		
		return list;
	}
}
