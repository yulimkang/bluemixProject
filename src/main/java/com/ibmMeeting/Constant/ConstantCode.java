package com.ibmMeeting.Constant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConstantCode {
	
	public static Integer SUCCESS= 1;
	public static Integer FAIL = 0;
	
	public static Integer ZERO = 0;
	
	public static Integer ReservationMaxMonth = 2;
	public static Integer ReservationMaxTime = 5;
	public static Integer ReservationEmailTime = 30;
	public static Integer ReservationNoShowCount = 3;
	public static Integer ReservationNoShowBanDay = 30;
	public static Integer ReservationMaxMonopoly = 5;

}
