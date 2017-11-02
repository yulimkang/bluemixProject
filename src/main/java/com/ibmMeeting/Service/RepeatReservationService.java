package com.ibmMeeting.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.AdminDao;
import com.ibmMeeting.Dao.MemberDao;
import com.ibmMeeting.Dao.RepeatReservationDao;
import com.ibmMeeting.VO.Member;
import com.ibmMeeting.VO.Reservation;

@Service
public class RepeatReservationService {

	@Autowired
	RepeatReservationDao repeatReservationDao;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	MemberService memberService;
	
	/* 
	 * 작성자 : 박성준
	 * 반복예약 가능 확인, 가능날짜, 불가능날짜 리턴
	 * 가능한 날짜와 불가능한 날짜를 ArrayList로 받아와 더 큰 ArrayList에 넣고 리턴한다.
	 */
	public ArrayList<ArrayList<String>>reservationAvailableCheck(HttpServletRequest request)
			throws ParseException {

		// 사용가능한 날짜와 불가능한 날짜를 넣을 ArrayList
		ArrayList<ArrayList<String>> reservationRepeatArray = new ArrayList<ArrayList<String>>();
		// 사용가능한 날짜
		ArrayList<String> availableDate = new ArrayList<String>();
		// 불가능한 날짜
		ArrayList<String> duplicateDate = new ArrayList<String>();
		
		// JSP에서 받아온 값들 HashMap에 저장
		HashMap<String, Object> repeatInformation = new HashMap<String, Object>();
		String startDate = request.getParameter("rsvStartDate");
		String endDate = request.getParameter("rsvEndDate");
		String startTime = request.getParameter("rsvStartTime");
		String endTime = request.getParameter("rsvEndTime");
		String confName = request.getParameter("rsvConfName");
		String repeatPeriod = request.getParameter("repeatPeriod");
		String repeatSetting = request.getParameter("repeatSetting");
		
		repeatInformation.put("startDate", startDate);
		repeatInformation.put("endDate", endDate);
		repeatInformation.put("startTime", startTime);
		repeatInformation.put("endTime", endTime);
		repeatInformation.put("confName", confName);
		repeatInformation.put("repeatSetting", repeatSetting);
		
		// startCal , endCal Calendar init
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();

		// 요일 배열
		String[] weekDay = { "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };

		// 데이터 포맷 정의
		DateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		// 중복 변수 선언 및 초기화
		Integer duplicateCount = ConstantCode.ZERO;

		// startCal, endCal 을 사용자가 입력한 시간으로 넣어줌
		Date startDateFormat = transFormat.parse(startDate);
		Date endDateFormat = transFormat.parse(endDate);
		startCal.setTime(startDateFormat);
		endCal.setTime(endDateFormat);

		/*
		 * dataCompare 은 startCal과 endCal 비교 dataCompare > 0 이면 startCal이 더 큰값
		 * dataCompare < 0 이면 endCal이 더 큰 값
		 */
		int dateCompare = startCal.compareTo(endCal);
		int compareTotalCount = ConstantCode.ZERO;
		int reservationAvailableCount = ConstantCode.ZERO;
		int reservationDuplicateCount = ConstantCode.ZERO;
		String availableDate1[] = new String[50];
		String duplicateDate2[] = new String[50];
		

		// 사용자가 설정한 반복주기가 '매일'이라면
		if (repeatPeriod.equals("day")) {
			
			// 시작날짜가 마지막날짜보다 클때까지 반복
			while (true) {
				dateCompare = startCal.compareTo(endCal);
				// startDateFormat > endDateFormat
				if (dateCompare > ConstantCode.ZERO) {
					break;
				}
				// 현재 날짜에 겹치는지 확인하여 개수를 return
				duplicateCount = repeatReservationDao.repeatCheck(repeatInformation);

				 // 개수가 0 개라면 사용가능한 날짜배열에 사용가능한 날짜 넣어줌
				if (duplicateCount == ConstantCode.ZERO) {
					availableDate.add(transFormat.format(startCal.getTime()));
					reservationAvailableCount++;
				// 개수가 0개 이상이라면 불가능하기에 불가능한 날짜배열에 불가능한 날짜 정보 넣어줌
				} else {
					duplicateDate.add(transFormat.format(startCal.getTime()));
					reservationDuplicateCount++;
					compareTotalCount++;
				}

				// 현재날짜에 하루를 더함
				startCal.add(startCal.DATE, ConstantCode.DAYS1);
				repeatInformation.put("startDate",transFormat.format(startCal.getTime()));
				
			}
			
			// 반복주기가 '주' 라면 도는 방식은 위와 같음
		} else if (repeatPeriod.equals("week")) {
			
			while (true) {
				dateCompare = startCal.compareTo(endCal);
				// startDateFormat > endDateFormat
				if (dateCompare > ConstantCode.ZERO) {
					break;
				}
				duplicateCount = repeatReservationDao.repeatCheck(repeatInformation);
				
				if(duplicateCount==ConstantCode.ZERO){
					availableDate.add(transFormat.format(startCal.getTime()));
					reservationAvailableCount++;
				}
				else{
					duplicateDate.add(transFormat.format(startCal.getTime()));
					reservationDuplicateCount++;
					compareTotalCount++;
				}
				
				// 반복 설정값에따라 더하는 값이 다름
				if (repeatSetting.equals("1week")) {
					startCal.add(startCal.DATE, ConstantCode.DAYS7);
				} else if (repeatSetting.equals("2week")) {
					startCal.add(startCal.DATE, ConstantCode.DAYS14);
				} else if (repeatSetting.equals("3week")) {
					startCal.add(startCal.DATE, ConstantCode.DAYS21);
				} else if (repeatSetting.equals("4week")) {
					startCal.add(startCal.DATE, ConstantCode.DAYS28);
				} else {
					System.out.println("error");
					break;
				}
				repeatInformation.put("startDate",transFormat.format(startCal.getTime()));
			}
			// 반복주기가 월이라면
		} else if (repeatPeriod.equals("month")) {

			// 반복주기가 주(ex 매달 첫째주 목요일)
			if (repeatSetting.equals("monthWeekRepeat")) {
				String week = String.valueOf(startCal.get(Calendar.WEEK_OF_MONTH));
				int weekNum = startCal.get(Calendar.DAY_OF_WEEK);
				String dayOfTheWeek = weekDay[weekNum + ConstantCode.ONE];

				while (true) {
					dateCompare = startCal.compareTo(endCal);
					if (dateCompare > ConstantCode.ZERO) {
						break;
					} else {
						duplicateCount = repeatReservationDao.repeatCheck(repeatInformation);
						
						if(duplicateCount==ConstantCode.ZERO){
							availableDate.add(transFormat.format(startCal.getTime()));
							reservationAvailableCount++;
						}
						else{
							duplicateDate.add(transFormat.format(startCal.getTime()));
							reservationDuplicateCount++;
							compareTotalCount++;
						}

						startCal.set(Calendar.YEAR, startCal.get(Calendar.YEAR));
						startCal.set(Calendar.MONTH,startCal.get(Calendar.MONTH) + ConstantCode.ONE);
						startCal.set(Calendar.WEEK_OF_MONTH,startCal.get(Calendar.WEEK_OF_MONTH));
						startCal.set(Calendar.DAY_OF_WEEK, weekNum);
						repeatInformation.put("startDate",transFormat.format(startCal.getTime()));
					}
				}
			}

			// 반복주기가 월 특정일 (ex : 매달 17일)
			else if (repeatSetting.equals("monthDayRepeat")) {
				while (true) {
					dateCompare = startCal.compareTo(endCal);
					if (dateCompare > ConstantCode.ZERO) {
						break;
					} else {
						duplicateCount = repeatReservationDao.repeatCheck(repeatInformation);
						
						if(duplicateCount==ConstantCode.ZERO){
							availableDate.add(transFormat.format(startCal.getTime()));
							reservationAvailableCount++;
						}
						else{
							duplicateDate.add(transFormat.format(startCal.getTime()));
							reservationDuplicateCount++;
							compareTotalCount++;
						}
							
						startCal.set(Calendar.YEAR,startCal.get(Calendar.YEAR));
						startCal.set(Calendar.MONTH,startCal.get(Calendar.MONTH)+ ConstantCode.ONE);
						repeatInformation.put("startDate",transFormat.format(startCal.getTime()));
						
					}
				}
			}
		}
		
		// 큰 Array에 되는날짜와 안되는 날짜를 add
		reservationRepeatArray.add(availableDate);
		reservationRepeatArray.add(duplicateDate);
		
		return reservationRepeatArray;
	}
	
	/* 
	 * 작성자 : 박성준
	 * 반복예약 신청
	 * 사용자가 입력한 값을 받아와 신청
	 */
	public Integer repeatReservationSubmit(HttpServletRequest request) throws MessagingException{
		
		String rsvRegDate = commonService.nowTime();
		String rsvStartTime = request.getParameter("rsvStartTime");
		String rsvEndTime = request.getParameter("rsvEndTime");
		String rsvConfNo = request.getParameter("rsvConfName");
		String repeatPeriod = request.getParameter("repeatPeriod");
		String repeatSetting = request.getParameter("repeatSetting");
		String rsvColor = request.getParameter("rsvColor");
		String rsvTitle = request.getParameter("rsvTitle");
		String rsvDelPwd = request.getParameter("rsvDelPwd");
		String rsvMemPn = request.getParameter("rsvMemPn");
		String rsvMemNm = request.getParameter("rsvMemNm");
		String rsvMemEm = request.getParameter("rsvMemEm");
		String rsvDescription = request.getParameter("rsvDescription");
		String availableDate = request.getParameter("availableDate");
		String rsvTotalTime = request.getParameter("rsvTotalTime");
		String emailCheck = request.getParameter("emailCheckValue");
		String[] availableDates;
		
		// 사용가능한 날짜를 , 간격으로 잘라내 저장
		availableDates = availableDate.split(",");
		
		// 반복예약이 하나도 없다면 반복예약 첫 값을 1로 설정
		Integer repeatNo;
		if(repeatReservationDao.repeatSeqMaxValue()==null){
			repeatNo = ConstantCode.ONE;
		}
		// 반복예약 seq중에 가장 큰 값에 1을 더하여 반복 seq저장
		else{
			repeatNo = repeatReservationDao.repeatSeqMaxValue() + ConstantCode.ONE;
		}

		HashMap<String,Object> repeatInformation = new HashMap<String,Object>();
		repeatInformation.put("nowDate",rsvRegDate);
		repeatInformation.put("rsvStartTime",rsvStartTime);
		repeatInformation.put("rsvEndTime",rsvEndTime);
		repeatInformation.put("rsvConfNo",rsvConfNo);
		repeatInformation.put("repeatPeriod",repeatPeriod);
		repeatInformation.put("repeatSetting",repeatSetting);
		repeatInformation.put("rsvColor",rsvColor);
		repeatInformation.put("rsvTitle",rsvTitle);
		repeatInformation.put("rsvDelPwd",rsvDelPwd);
		repeatInformation.put("rsvMemPn",rsvMemPn);
		repeatInformation.put("rsvMemNm",rsvMemNm);
		repeatInformation.put("rsvMemEm",rsvMemEm);
		repeatInformation.put("rsvDescription",rsvDescription);
		repeatInformation.put("rsvTotalTime",rsvTotalTime);
		repeatInformation.put("repeatNo", repeatNo);
		
		if(emailCheck.equals("true"))
			repeatInformation.put("emailCheck","Y");
		else
			repeatInformation.put("emailCheck","N");
		
		// 가능한 날짜만큼 for문을 돌려 d/b update
		for(int i=ConstantCode.ZERO; i<availableDates.length; i++){
			repeatInformation.put("rsvDate", availableDates[i]);
			repeatReservationDao.repeatReservationSubmit(repeatInformation);
		}
		
		// 사용자가 현재 존재하는지 확인
		int exist = memberService.checkExistMemInfo(rsvMemPn);
		
		// 사용자 정보가 없다면 사용자 정보 input
		if(exist==0){
			
			Member member =  new Member();
			Date date = new Date();

			member.setMemName(rsvMemNm);
			member.setMemPn(rsvMemPn);
			member.setMemEm(rsvMemEm);
			member.setMemComp(ConstantCode.COMPANY_NAME);
			member.setMemRegDate(date);
			member.setMemState(ConstantCode.NORMAL);
			member.setCountWarn(ConstantCode.ZERO);
			member.setMemBanday(ConstantCode.NOT);
			
			memberDao.registMember(member);
			
		}
		
		// 이메일 전송
		String adminEmail = adminDao.getAdminEmail();
			
		String subject = "[IBM 회의실] 반복예약 신청이 접수됐습니다.";
		String contentTitle = "회의제목 : " + rsvTitle;
		String contentStartTime = "회의시작시간 : " + rsvStartTime;
		String contentInformation = rsvMemNm+ "님이 " + rsvRegDate + " 날짜 반복예약 신청하셨습니다.";
		String contentURL = ConstantCode.URL;
		String contentTotal = contentTitle+"\n"+ "\n "+ contentStartTime + "\n "+ contentInformation+"\n"+ " " +contentURL;
		
		commonService.sendEmail(adminEmail, subject, contentTotal);
		
		return ConstantCode.SUCCESS;
	}
	
}
