package com.ibmMeeting.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ibmMeeting.VO.Reservation;

@Mapper
public interface ReservationDao {

	List<Reservation> getReservation();
	
	List<Reservation> getReservationInfo(int no);
}
