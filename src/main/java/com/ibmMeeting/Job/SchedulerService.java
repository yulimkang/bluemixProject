package com.ibmMeeting.Job;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ibmMeeting.Constant.ConstantCode;
import com.ibmMeeting.Dao.BoardingDao;
import com.ibmMeeting.Dao.SchedulerDao;
import com.ibmMeeting.Dao.SettingDao;
import com.ibmMeeting.Service.CommonService;

@Component
public class SchedulerService {

	@Autowired
	BoardingDao boardingDao;

	@Autowired
	SchedulerDao schedulerDao;

	@Autowired
	SettingDao settingDao;

	@Autowired
	CommonService commonService;
	
	/* 
	 * 작성자 : 박성준
	 * 매일 12시 5분
	 * noShow 차단 해제 해당자 해제
	 */
	@Scheduled(cron ="0 5 0 * * *")
	public void banReset(){
		String today = commonService.nowTimeDash();
		boardingDao.noShowBanReset(today);
	}

	/* 
	 * 작성자 : 박성준
	 * 매월 1일 0시 0분 0초에 실행한다.
	 * 모든 유저의 noShowCount 초기화
	 */
	@Scheduled(cron = "0 0 0 1 * *")
	public void noShowCountAllInit() {
		boardingDao.noShowAllUserInit();
	}

	/* 
	 * 작성자 : 박성준
	 * 애플리케이션 시작 후 60초 후에 첫 실행, 그 후 매 120초마다 주기적으로 실행
	 * 이메일 보낼 대상자들 선정하여 이메일 전송, 회의 예약 30분전 테스트
	 */
	@Scheduled(initialDelay = 60000, fixedDelay = 120000)
	public void otherJob() {

		//셋팅값에서 이메일 값 가져옴
		HashMap<String, Object> settingValue = settingDao.settingLoad();
		Integer emailTime = (Integer) settingValue.get("SET_EMAIL_TIME");

		//현재시간에 이메일 셋팅값을 더함
		Calendar nowTimeCal = Calendar.getInstance();
		nowTimeCal.add(Calendar.MINUTE, emailTime);
		int hour = nowTimeCal.get(Calendar.HOUR_OF_DAY);
		int min = nowTimeCal.get(Calendar.MINUTE);

		String nowDate = commonService.nowTime();
		String nowTime = Integer.toString(hour) + ":" + Integer.toString(min)+ ":00";
		
		HashMap<String, Object> dateInformation = new HashMap<String, Object>();
		dateInformation.put("nowDate", nowDate);
		dateInformation.put("nowTime", nowTime);

		// 셋팅값을 더한시간보다 미팅시작시간이 작고 이메일 체크가 돼 있다면
		// 그 reservation 값을 가져옴
		ArrayList<HashMap<String, Object>> emailSendUserInfo = schedulerDao.reservaionEmailInfo(dateInformation);
		
		if (!emailSendUserInfo.isEmpty()) {
			for (int i = 0; i < emailSendUserInfo.size(); i++) {

				Integer rsvNumber = (Integer) emailSendUserInfo.get(i).get("RSV_NO");
				String rsvMemberEmail = (String) emailSendUserInfo.get(i).get("RSV_MEM_EM");
				String rsvConfNm = (String) emailSendUserInfo.get(i).get("CONF_NM");
				Time rsvStartTime = (Time) emailSendUserInfo.get(i).get("RSV_START_TIME");
				String rsvTitle = (String) emailSendUserInfo.get(i).get("RSV_TITLE");
				String rsvMemNm = (String) emailSendUserInfo.get(i).get("RSV_MEM_NM");

				String subject = "[IBM 회의실] " + rsvMemNm+ "님 " +rsvTitle + " 회의 약 30분 전입니다.";
				String contentTitle = "회의제목 : " + rsvTitle;
				String contentStartTime = "회의시작시간 : " + rsvStartTime;
				String contentConfNm = "회의실 : " + rsvConfNm;
				String contentURL = ConstantCode.URL;
				String contentTotal = contentTitle+"\n"+ contentStartTime+"\n"+ contentConfNm +"\n"+ contentURL;
				
				commonService.sendEmail(rsvMemberEmail, subject, contentTotal);
				
				schedulerDao.eMailSendStateUpdate(rsvNumber);
			}
		}
	}
}
