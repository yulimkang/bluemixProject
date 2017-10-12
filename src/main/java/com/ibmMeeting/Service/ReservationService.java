package com.ibmMeeting.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Dao.ReservationDao;
import com.ibmMeeting.VO.Reservation;

@Service
public class ReservationService {

	@Autowired
	ReservationDao reservationDao;
	
	public List<Reservation> getReservation(){
		
		List<Reservation> list = reservationDao.getReservation();
		
		return list;
	}
	
	public List<Reservation> getReservationInfo(int no){
		
		List<Reservation> list = reservationDao.getReservationInfo(no);

		return list;
	}
	
}
