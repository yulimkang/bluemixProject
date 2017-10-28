package com.ibmMeeting.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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
	
	public ArrayList<ArrayList<String>>reservationAvailableCheck(HttpServletRequest request)
			throws ParseException {

		ArrayList<ArrayList<String>> reservationRepeatArray = new ArrayList<ArrayList<String>>();
		ArrayList<String> availableDate = new ArrayList<String>();
		ArrayList<String> duplicateDate = new ArrayList<String>();
		
		// jsp input value store
		HashMap<String, Object> repeatInformation = new HashMap<String, Object>();
		String startDate = request.getParameter("rsvStartDate");
		String endDate = request.getParameter("rsvEndDate");
		String startTime = request.getParameter("rsvStartTime");
		String endTime = request.getParameter("rsvEndTime");
		String confName = request.getParameter("rsvConfName");
		String repeatPeriod = request.getParameter("repeatPeriod");
		String repeatSetting = request.getParameter("repeatSetting");
		
		// hashmap store
		repeatInformation.put("startDate", startDate);
		repeatInformation.put("endDate", endDate);
		repeatInformation.put("startTime", startTime);
		repeatInformation.put("endTime", endTime);
		repeatInformation.put("confName", confName);
		repeatInformation.put("repeatSetting", repeatSetting);
		
		System.out.println(repeatInformation);

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
		

		if (repeatPeriod.equals("day")) {
			
			while (true) {
				dateCompare = startCal.compareTo(endCal);
				// startDateFormat > endDateFormat
				if (dateCompare > ConstantCode.ZERO) {
					break;
				}
				duplicateCount = repeatReservationDao.repeatCheck(repeatInformation);

				if (duplicateCount == ConstantCode.ZERO) {
					availableDate.add(transFormat.format(startCal.getTime()));
					reservationAvailableCount++;
				} else {
					duplicateDate.add(transFormat.format(startCal.getTime()));
					reservationDuplicateCount++;
					compareTotalCount++;
				}

				startCal.add(startCal.DATE, ConstantCode.DAYS1);
				repeatInformation.put("startDate",transFormat.format(startCal.getTime()));
				
			}
			
		} else if (repeatPeriod.equals("week")) {
			
//			String mondayCheck = request.getParameter("mon");
//			String tueCheck = request.getParameter("tue");
//			String wedCheck = request.getParameter("wed");
//			String thuCheck = request.getParameter("thu");
//			String friCheck = request.getParameter("fri");
//			
//			int startCalWeek = startCal.DAY_OF_WEEK;
//			// 1= 일 ~ 토=7
//			
//			if(mondayCheck.equals("true")){
//				
//			}

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
		} else if (repeatPeriod.equals("month")) {

			// week repeat
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

			// day repeat
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
		
		reservationRepeatArray.add(availableDate);
		reservationRepeatArray.add(duplicateDate);
		
		return reservationRepeatArray;
	}
	
	
	public Integer repeatReservationSubmit(HttpServletRequest request){
		
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
		availableDates = availableDate.split(",");
		
		Integer repeatNo;
		if(repeatReservationDao.repeatSeqMaxValue()==null){
			repeatNo = ConstantCode.ONE;
		}
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
		
		for(int i=ConstantCode.ZERO; i<availableDates.length; i++){
			repeatInformation.put("rsvDate", availableDates[i]);
			repeatReservationDao.repeatReservationSubmit(repeatInformation);
		}
		
		int exist = memberService.checkExistMemInfo(rsvMemPn);
		
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
